/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polygonetic.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.swing.*;

/**
 * @author polygOnetic
 */
public class ConfigManager {

	private ConfigManager() {

	}

	public static final String LAST_FILE = "lastFile";
	public static final String SLIDER_VALUE = "sliderValue";
	public static final String ARCH = "arch";
	public static final String LIMIT = "limit";
	public static final String PROCESS_COUNT = "processCount";
	public static final String ROAMING_DIR = getAppDataDirectory("GuetzliConverter", true);
	public static final File CONFIG_FILE = new File(ROAMING_DIR + "config.properties");
	public static final String CONFIG_OS = "Windows";  // TODO: maven build param -> config os?

	public static boolean saveConfig(String lastPath, int arch, int memlimit, int slider, int processcount) {

		try {
			Properties props = new Properties();
			props.setProperty(LAST_FILE, lastPath);
			props.setProperty(SLIDER_VALUE, String.valueOf(slider));
			props.setProperty(ARCH, String.valueOf(arch));
			props.setProperty(LIMIT, String.valueOf(memlimit));
			props.setProperty(PROCESS_COUNT, String.valueOf(processcount-1));
			FileWriter writer = new FileWriter(CONFIG_FILE);
			props.store(writer, "Guetzli GUI settings");
			writer.close();
			return true;
		} catch (IOException ex) {
			return false;
		}

	}

	public static String loadConfig(JComboBox archBox, JComboBox memBox, JSlider slider, JLabel sliderText,
			boolean setGuiSettings, JComboBox processCount) {
		try {

			try (FileReader reader = new FileReader(CONFIG_FILE)) {
				Properties props = new Properties();
				props.load(reader);
				String lastFile = "";
				String sliderValue = "85";
				String arch = "0";
				String limit = "0";
				String process = "0";
				if (props.getProperty(LAST_FILE) != null) {
					lastFile = props.getProperty(LAST_FILE);
				}
				if (props.getProperty(SLIDER_VALUE) != null) {
					sliderValue = props.getProperty(SLIDER_VALUE);
				}
				if (props.getProperty(ARCH) != null) {
					arch = props.getProperty(ARCH);
				}
				if (props.getProperty(LIMIT) != null) {
					limit = props.getProperty(LIMIT);
				}
				if (props.getProperty(PROCESS_COUNT) != null) {
					process = props.getProperty(PROCESS_COUNT);
				}
				if (setGuiSettings) {
					final int archMinMax = Math.min(Integer.parseInt(arch), archBox.getItemCount());
					archBox.setSelectedIndex(archMinMax);

					final int memBoxMinMax = Math.min(Integer.parseInt(limit), archBox.getItemCount());
					memBox.setSelectedIndex(memBoxMinMax);

					slider.setValue(Integer.parseInt(sliderValue));
					sliderText.setText(sliderValue);

					final int procCount = Math.min(Integer.parseInt(process), processCount.getItemCount()-1);
					processCount.setSelectedIndex(procCount);
				}
				return lastFile;
			}


		} catch (IOException ex) {
			// file does not exist
			return "";
		}// I/O error

	}


	public static String getAppDataDirectory(final String subDirectory, boolean create) {

		String appDataDirectory;
		try {

			// TODO: ggf. mit StringUtils.defaultString -> commons-lang3 arbeiten
			appDataDirectory = System.getenv("APPDATA");
			if (appDataDirectory == null) {
				appDataDirectory = System.getenv("HOME");
				if (appDataDirectory == null) {
					appDataDirectory = tryDiscoverTempDirectory(subDirectory);
				} else {
					appDataDirectory += File.separator + "." + subDirectory + File.separator;
				}
			} else {
				appDataDirectory += File.separator + subDirectory + File.separator;
			}

		} catch (Exception e) {
			e.printStackTrace();
			appDataDirectory = "";
		}

		if (create && appDataDirectory.length() > 0) {
			try {
				File dir = new File(appDataDirectory);
				dir.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return appDataDirectory;
	}


	private static String tryDiscoverTempDirectory(final String subDirectory) throws Exception {
		String appDataDirectory = System.getenv("TEMP");

		if (appDataDirectory == null) {
			throw new Exception("Could not access APPDATA, HOME or TEMP environment variables");
		}

		if (CONFIG_OS.equals("Windows")) {
			appDataDirectory += File.separator + subDirectory + File.separator;
		} else {
			appDataDirectory += File.separator + "." + subDirectory + File.separator;
		}

		return appDataDirectory;
	}
}
