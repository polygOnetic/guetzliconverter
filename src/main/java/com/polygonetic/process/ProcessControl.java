package com.polygonetic.process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import com.polygonetic.GuetzliConverter;
import com.polygonetic.config.ConfigManager;
import com.polygonetic.gui.MemoryAmount;

public class ProcessControl extends Thread {

	private List<File> selectedFiles;
	private List<File> workingFiles;
	private Queue<File> fileQueue;
	private JLabel labelProgress;
	private JButton buttonConvert;

	private int processCount;
	private String architecture;
	private MemoryAmount mem;
	private int sliderQuality;
	private JTextArea textAreaLogging;

	public ProcessControl() {
		reset();
	}

	public void setSelectedFiles(final List<File> selectedFiles) {
		this.selectedFiles = selectedFiles;
	}

	public void setLabelProgress(final JLabel labelProgress) {
		this.labelProgress = labelProgress;
	}

	public void setButtonConvert(final JButton buttonConvert) {
		this.buttonConvert = buttonConvert;
	}

	public void initRun(final int processCount, final String architecture, final int archIndex, final MemoryAmount mem,
			final int sliderQuality, final JTextArea textAreaLogging) {

		fileQueue = new ArrayDeque<>(selectedFiles);

		// save config first
		if (!selectedFiles.isEmpty()) {
			ConfigManager.saveConfig(String.valueOf(selectedFiles.get(0)), archIndex, mem.ordinal(),
					sliderQuality, processCount);
		}

		this.processCount = processCount;
		this.architecture = architecture;
		this.mem = mem;
		this.sliderQuality = sliderQuality;
		this.textAreaLogging = textAreaLogging;
	}

	public void run() {
		proceedWithNextBatch();
	}

	private void proceedWithNextBatch() {
		if (workingFiles.size() < processCount && !fileQueue.isEmpty()) {
			textAreaLogging.append("Creating new batch. Remaining files: " + fileQueue.size() + "\n");
			int batchcount = 0;
			for (int i = 0; i < processCount; i++) {
				final File currentFile = fileQueue.remove();
				workingFiles.add(currentFile);
				startConvertThread(architecture, mem, sliderQuality, textAreaLogging, buttonConvert, currentFile);
				textAreaLogging.append("Converting " + currentFile.getName() + "\n");
				if (workingFiles.size() >= processCount || fileQueue.isEmpty()) {
					break;
				}
				++batchcount;
			}
			if(batchcount > 0) {
				textAreaLogging.append("Created " + batchcount + " guetzli threads for working.\n");
			}
		}
	}

	public void reportFinished(final File finishedFile) {
		workingFiles.remove(finishedFile);

		if (workingFiles.isEmpty() && fileQueue.isEmpty()) {
			labelProgress.setIcon(new javax.swing.ImageIcon(GuetzliConverter.createUrl("/ready.png")));
			labelProgress.setEnabled(true);
			buttonConvert.setEnabled(false);
			selectedFiles = new ArrayList<>();
			reset();
		} else {
			proceedWithNextBatch();
		}
	}

	public void startConvertThread(final String architecture, final MemoryAmount mem,
			final int sliderQuality, final JTextArea textAreaLogging, final JButton buttonConvert, final File image) {

		final String memLimit = createMemoryParam(mem);
		final String pathToGuetzli = obtainPathToGuetzli(architecture);

		String destinationFileName = image.getAbsolutePath().replaceFirst("[.][^.]+$", "");
		destinationFileName += "_guetzli.jpg";
		final File destinationFile = new File(destinationFileName);

		String execCommandContent = pathToGuetzli + " --verbose --quality "
				+ sliderQuality + memLimit + "'"
				+ image.toString() + "' '" + destinationFile.toString() + "'";

		String execCommand = execCommandContent;
		execCommand = createUnixCommand(execCommandContent, execCommand);

		textAreaLogging.append(ProcessExecutor.dateTimeNow() + execCommandContent + "\n");
		final ProcessExecutor worker = new ProcessExecutor(execCommand, textAreaLogging);
		worker.setProcessControl(this);
		worker.setMyFile(image);
		worker.start();

	}

	private String createUnixCommand(final String execCommandContent, String execCommand) {

		if (ConfigManager.CONFIG_OS.equals("MacOS") || ConfigManager.CONFIG_OS.equals("Linux")) {
			try {
				final File temp = File.createTempFile("gExec", ".sh");
				try (final BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {
					bw.write(execCommandContent);
				}
				if (temp.setExecutable(true)) {
					execCommand = "/bin/sh -c \"" + temp.getAbsolutePath() + "\"";
					return execCommand;
				}
			} catch (IOException ex) {
				Logger.getLogger(GuetzliConverter.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return execCommand;
	}

	private String obtainPathToGuetzli(final String architecture) {
		String pathToGuetzli = "guetzli_windows_x86-64.exe";
		if (ConfigManager.CONFIG_OS.equals("Windows")) {
			if (architecture.equals("32bit")) {
				pathToGuetzli = "guetzli_windows_x86.exe";
			}
		}

		if (ConfigManager.CONFIG_OS.equals("MacOS")) {
			String pathWithFile = GuetzliConverter.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String path = pathWithFile.substring(0, pathWithFile.lastIndexOf("/") + 1);
			pathToGuetzli = "'" + path + "guetzli_darwin_x86-64'";

		}

		if (ConfigManager.CONFIG_OS.equals("Linux")) {
			String pathWithFile = GuetzliConverter.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String path = pathWithFile.substring(0, pathWithFile.lastIndexOf("/") + 1);
			pathToGuetzli = "'" + path + "guetzli_linux_x86-64'";
		}
		return pathToGuetzli;
	}


	private String createMemoryParam(final MemoryAmount mem) {

		if (mem == null) {
			return " --nomemlimit ";
		}

		switch (mem) {
			case GB_2:
				return " --memlimit 2000 ";
			case GB_4:
				return " --memlimit 4000 ";
			case GB_6:
				return " --memlimit 6000 ";
			case GB_8:
				return " --memlimit 8000 ";
			case GB_16:
				return " --memlimit 16000 ";
			case NO_LIMIT:
			default:
				return " --nomemlimit ";
		}

	}

	public void reset() {
		selectedFiles = new ArrayList<>();
		workingFiles = new ArrayList<>();
		fileQueue = new ArrayDeque<>();
	}


}
