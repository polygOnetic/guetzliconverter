package com.polygonetic.process;

import java.io.OutputStream;
import java.util.concurrent.Callable;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.ShutdownHookProcessDestroyer;

class ProcessCallable implements Callable<Long> {

	private final long watchdogTimeout;
	private final ProcessExecutorHandler handler;
	private final CommandLine commandline;

	ProcessCallable(final long watchdogTimeout, final ProcessExecutorHandler handler, final CommandLine commandline) {
		this.watchdogTimeout = watchdogTimeout;
		this.handler = handler;
		this.commandline = commandline;
	}

	@Override
	public Long call() throws Exception {
		final Executor executor = new DefaultExecutor();
		executor.setProcessDestroyer(new ShutdownHookProcessDestroyer());

		final ExecuteWatchdog watchDog = new ExecuteWatchdog(watchdogTimeout);
		executor.setWatchdog(watchDog);
		final OutputStream forwardStdFalse = new MyLogOutputStream(handler, false);
		final OutputStream forwardStdTrue = new MyLogOutputStream(handler, true);
		final ExecuteStreamHandler executeStreamHandler = new PumpStreamHandler(forwardStdTrue, forwardStdFalse);
		executor.setStreamHandler(executeStreamHandler);

		long exitValue;
		try {
			exitValue = executor.execute(commandline);
		} catch (ExecuteException e) {
			exitValue = e.getExitValue();
		}
		if (watchDog.killedProcess()) {
			exitValue = ProcessExecutor.WATCHDOG_EXIST_VALUE;
		}
		return exitValue;
	}
}
