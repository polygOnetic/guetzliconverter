/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guetzliconverter;

import org.apache.commons.exec.Executor;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.LogOutputStream;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.ShutdownHookProcessDestroyer;

/**
 *
 * @author polygOnetic
 */
public class ProcessExecutor extends Thread {

    String execCommand;
    JLabel labelProgress;
    JButton buttonConvert;
    JTextArea textAreaLogging;

    public ProcessExecutor(String execCommand, JTextArea textAreaLogging, JLabel labelProgress, JButton buttonConvert) {
        this.execCommand = execCommand;
        this.labelProgress = labelProgress;
        this.buttonConvert = buttonConvert;
        this.textAreaLogging = textAreaLogging;
    }

    interface ProcessExecutorHandler {

        public void onStandardOutput(String line);

        public void onStandardError(String line);
    }
    public static final Long WATCHDOG_EXIST_VALUE = -999L;

    public static Future<Long> runProcess(final CommandLine commandline, final ProcessExecutorHandler handler, final long watchdogTimeout) throws IOException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        return executor.submit(new ProcessCallable(999999999, handler, commandline));
    }

    private static class ProcessCallable implements Callable<Long> {

        private long watchdogTimeout;
        private ProcessExecutorHandler handler;
        private CommandLine commandline;

        private ProcessCallable(long watchdogTimeout, ProcessExecutorHandler handler, CommandLine commandline) {
            this.watchdogTimeout = watchdogTimeout;
            this.handler = handler;
            this.commandline = commandline;
        }

        @Override
        public Long call() throws Exception {
            Executor executor = new DefaultExecutor();
            executor.setProcessDestroyer(new ShutdownHookProcessDestroyer());
            ExecuteWatchdog watchDog = new ExecuteWatchdog(watchdogTimeout);
            executor.setWatchdog(watchDog);
            executor.setStreamHandler(new PumpStreamHandler(new MyLogOutputStream(handler, true), new MyLogOutputStream(handler, false)));
            Long exitValue;
            try {
                exitValue = new Long(executor.execute(commandline));
            } catch (ExecuteException e) {
                exitValue = new Long(e.getExitValue());
            }
            if (watchDog.killedProcess()) {
                exitValue = WATCHDOG_EXIST_VALUE;
            }
            return exitValue;
        }
    }

    private static class MyLogOutputStream extends LogOutputStream {

        private ProcessExecutorHandler handler;
        private boolean forewordToStandardOutput;

        private MyLogOutputStream(ProcessExecutorHandler handler, boolean forewordToStandardOutput) {
            this.handler = handler;
            this.forewordToStandardOutput = forewordToStandardOutput;
        }

        @Override
        protected void processLine(String line, int level) {
            if (forewordToStandardOutput) {
                handler.onStandardOutput(line);
            } else {
                handler.onStandardError(line);
            }
        }
    }

    public static void startMyProcess(String[] args, String execCommand, JTextArea textAreaLogging, JLabel labelProgress, JButton buttonConvert) throws IOException {
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
        }, 1);
        try {
            Long aVoid = exitValue.get();
            GuetzliConverter.processNextRun();
            textAreaLogging.append(dateTimeNow() + "Finished with  " + aVoid + "\n");
        } catch (InterruptedException e) {
            GuetzliConverter.processNextRun();
            e.printStackTrace();
        } catch (ExecutionException e) {
            GuetzliConverter.processNextRun();
            e.printStackTrace();
        }
    }

    public static String dateTimeNow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter) + ": ";
        return formattedDateTime;
    }

    public void run() {
        String[] cmds = {};
        try {
            startMyProcess(cmds, execCommand, textAreaLogging, labelProgress, buttonConvert);
        } catch (IOException ex) {
            Logger.getLogger(ProcessExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
