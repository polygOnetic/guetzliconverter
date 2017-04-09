/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guetzliconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;

/**
 *
 * @author polygOnetic
 */
public class ConfigManager {

    public static String roamingDir = getAppDataDirectory("GuetzliConverter", true);
    public static File configFile = new File(roamingDir + "config.properties");
    public static String configOS = "Windows";

    public static boolean saveConfig(String lastPath, int arch, int memlimit, int slider, int processcount) {

        try {
            Properties props = new Properties();
            props.setProperty("lastFile", lastPath);
            props.setProperty("sliderValue", String.valueOf(slider));
            props.setProperty("arch", String.valueOf(arch));
            props.setProperty("limit", String.valueOf(memlimit));
            props.setProperty("processCount", String.valueOf(processcount));
            FileWriter writer = new FileWriter(configFile);
            props.store(writer, "Guetzli GUI settings");
            writer.close();
            return true;
        } catch (FileNotFoundException ex) {
            return false;
            // file does not exist
        } catch (IOException ex) {
            return false;
            // I/O error
        }
    }

    public static String loadConfig(JComboBox archBox, JComboBox memBox, JSlider slider, JLabel sliderText, boolean setGuiSettings, JComboBox processCount) {
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            String lastFile = "";
            String sliderValue = "85";
            String arch = "0";
            String limit = "0";
            String process = "0";
            if (props.getProperty("lastFile") != null) {
                lastFile = props.getProperty("lastFile");
            }
            if (props.getProperty("sliderValue") != null) {
                sliderValue = props.getProperty("sliderValue");
            }
            if (props.getProperty("arch") != null) {
                arch = props.getProperty("arch");
            }
            if (props.getProperty("limit") != null) {
                limit = props.getProperty("limit");
            }
            if (props.getProperty("processCount") != null) {
                process = props.getProperty("processCount");
            }
            reader.close();
            if (setGuiSettings) {
                archBox.setSelectedIndex(Integer.parseInt(arch));
                memBox.setSelectedIndex(Integer.parseInt(limit));
                slider.setValue(Integer.parseInt(sliderValue));
                sliderText.setText(String.valueOf(sliderValue));
                processCount.setSelectedIndex(Integer.parseInt(process));
            }
            return lastFile;
        } catch (FileNotFoundException ex) {
            // file does not exist
            return "";
        } catch (IOException ex) {
            // I/O error
            return "";
        }

    }

    public static String getAppDataDirectory(String subDirectory, boolean create) {

        String appDataDirectory;
        try {

            appDataDirectory = System.getenv("APPDATA");

            if (appDataDirectory != null) {
                appDataDirectory += File.separator + subDirectory + File.separator;
            } else {
                appDataDirectory = System.getenv("HOME");
                if (appDataDirectory != null) {
                    appDataDirectory += File.separator + "." + subDirectory + File.separator;
                } else {
                    appDataDirectory = System.getenv("TEMP");

                    if (appDataDirectory == null) {
                        throw new Exception("Could not access APPDATA, HOME or TEMP environment variables");
                    }

                    if (ConfigManager.configOS.equals("Windows")) {
                        appDataDirectory += File.separator + subDirectory + File.separator;
                    } else {
                        appDataDirectory += File.separator + "." + subDirectory + File.separator;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            appDataDirectory = "";
        }

        if (create && appDataDirectory != null && appDataDirectory.length() > 0) {
            try {
                File dir = new File(appDataDirectory);
                dir.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appDataDirectory;
    }

}
