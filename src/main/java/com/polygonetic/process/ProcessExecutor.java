/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polygonetic.process;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.ExecuteWatchdog;

/**
 * @author polygOnetic
 */
public class ProcessExecutor extends Thread {

	public static final Long WATCHDOG_EXIST_VALUE = -999L;

	private String execCommand;
	private JTextArea textAreaLogging;

	private ProcessControl processControl;
	private File myFile;

	public ProcessExecutor(final String execCommand, final JTextArea textAreaLogging) {

		this.execCommand = execCommand;
		this.textAreaLogging = textAreaLogging;

	}

	public void setMyFile(final File myFile) {
		this.myFile = myFile;
	}

	public void setProcessControl(final ProcessControl processControl) {
		this.processControl = processControl;
	}

	public static Future<Long> runProcess(final CommandLine commandline, final ProcessExecutorHandler handler,
			final long watchdogTimeout) throws IOException {
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		return executor.submit(new ProcessCallable(watchdogTimeout, handler, commandline));
	}

	public void startMyProcess(final String execCommand, final JTextArea textAreaLogging) throws IOException {
		CommandLine cl = CommandLine.parse(execCommand);
		Future<Long> exitValue = runProcess(cl, new ProcessExecutorHandler() {
			@Override
			public void onStandardOutput(String msg) {
				textAreaLogging.append(dateTimeNow() + msg + "\n");
			}

			@Override
			public void onStandardError(String msg) {
				textAreaLogging.append(dateTimeNow() + msg + "\n");
			}
		}, ExecuteWatchdog.INFINITE_TIMEOUT); // timeout infinte -> we don't know how long guetzli runs.

		try {
			Long aVoid = exitValue.get();
			processControl.reportFinished(myFile);
			textAreaLogging.append(dateTimeNow() + "Finished with  " + aVoid + "\n");
		} catch (InterruptedException | ExecutionException e) {
			processControl.reportFinished(myFile);
			textAreaLogging.append("Interrupted or ExecutionException occured: " + e.getMessage() + "\n");
			// we don't want to interrupt here, yet.
		}
	}

	public static String dateTimeNow() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.now();
		return dateTime.format(formatter) + ": ";
	}

	public void run() {
		try {
			startMyProcess(execCommand, textAreaLogging);
		} catch (IOException ex) {
			Logger.getLogger(ProcessExecutor.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
