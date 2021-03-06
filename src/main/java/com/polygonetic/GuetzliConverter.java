package com.polygonetic;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.polygonetic.config.ConfigManager;
import com.polygonetic.gui.MemoryAmount;
import com.polygonetic.process.ProcessControl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author polygOnetic
 */
public class GuetzliConverter extends javax.swing.JFrame {

	public String convertType;

	private ProcessControl processControl;
	private List<File> fileList = new ArrayList<>();

	/**
	 * Creates new form MainGui
	 */
	public GuetzliConverter() {
		initComponents();
		setResizable(false);
		setIcon();
		final String txtField = ConfigManager.loadConfig(boxArch, boxLimit, sliderQuality, labelQualityValue, true, boxProcessCount);
		final String displayText = txtField != null && txtField.length() > 0 ? txtField : "No file selected ...";
		textFieldSelectedFile.setText(displayText);

		if (txtField != null && txtField.length() > 0) {
			final File selectedFile = new File(txtField);
			if (selectedFile.exists()) {
				if (selectedFile.isFile()) {
					fileList.add(selectedFile);
				} else if (selectedFile.isDirectory()) {
					displayDirectoryContents(selectedFile);
				}
				buttonConvert.setEnabled(true);
			}


		}


	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		labelHeader = new javax.swing.JLabel();
		paneMain = new javax.swing.JTabbedPane();
		tabConvert = new javax.swing.JPanel();
		buttonChooseFile = new javax.swing.JButton();
		buttonChooseFolder = new javax.swing.JButton();
		buttonConvert = new javax.swing.JButton();
		labelProgress = new javax.swing.JLabel();
		boxLimit = new javax.swing.JComboBox<>();
		labelQualityValue1 = new javax.swing.JLabel();
		boxProcessCount = new javax.swing.JComboBox<>();
		labelQualityValue2 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		labelQuality = new javax.swing.JLabel();
		sliderQuality = new javax.swing.JSlider();
		labelQualityValue = new javax.swing.JLabel();
		boxArch = new javax.swing.JComboBox<>();
		textFieldSelectedFile = new javax.swing.JTextField();
		tabLogging = new javax.swing.JPanel();
		scrollPaneLogging = new javax.swing.JScrollPane();
		textAreaLogging = new javax.swing.JTextArea();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Guetzli Converter");
		setBackground(new java.awt.Color(255, 255, 255));

		labelHeader.setIcon(new javax.swing.ImageIcon(createUrl("/header.png"))); // NOI18N

		buttonChooseFile.setIcon(new javax.swing.ImageIcon(createUrl("/btnChoose1.png"))); // NOI18N
		buttonChooseFile.setMargin(new java.awt.Insets(0, 0, 0, 0));
		buttonChooseFile.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				buttonChooseFileMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				buttonChooseFileMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				buttonChooseFileMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				buttonChooseFileMouseReleased(evt);
			}
		});
		buttonChooseFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonChooseFileActionPerformed(evt);
			}
		});

		buttonChooseFolder.setIcon(new javax.swing.ImageIcon(createUrl("/btnChooseF1.png"))); // NOI18N
		buttonChooseFolder.setMargin(new java.awt.Insets(0, 0, 0, 0));
		buttonChooseFolder.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				buttonChooseFolderMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				buttonChooseFolderMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				buttonChooseFolderMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				buttonChooseFolderMouseReleased(evt);
			}
		});
		buttonChooseFolder.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonChooseFolderActionPerformed(evt);
			}
		});

		buttonConvert.setIcon(new javax.swing.ImageIcon(createUrl("/btnConvert1.png"))); // NOI18N
		buttonConvert.setEnabled(false);
		buttonConvert.setMargin(new java.awt.Insets(0, 0, 0, 0));
		buttonConvert.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				buttonConvertMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				buttonConvertMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				buttonConvertMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				buttonConvertMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				buttonConvertMouseReleased(evt);
			}
		});
		buttonConvert.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonConvertActionPerformed(evt);
			}
		});

		labelProgress.setIcon(new javax.swing.ImageIcon(createUrl("/ready.png"))); // NOI18N

		boxLimit.setModel(new javax.swing.DefaultComboBoxModel<>(MemoryAmount.values()));
		boxLimit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				boxLimitActionPerformed(evt);
			}
		});

		labelQualityValue1.setText("Memory:");

		boxProcessCount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		boxProcessCount.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				boxProcessCountActionPerformed(evt);
			}
		});

		labelQualityValue2.setText("Process count:");

		jLabel1.setIcon(new javax.swing.ImageIcon(createUrl("/apphint.png"))); // NOI18N

		labelQuality.setText("Quality");

		sliderQuality.setMinimum(85);
		sliderQuality.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				sliderQualityMouseDragged(evt);
			}

			public void mouseMoved(java.awt.event.MouseEvent evt) {
				sliderQualityMouseMoved(evt);
			}
		});
		sliderQuality.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				sliderQualityMouseClicked(evt);
			}
		});
		sliderQuality.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				sliderQualityPropertyChange(evt);
			}
		});

		labelQualityValue.setText("85");

		boxArch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"64bit", "32bit"}));
		boxArch.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				boxArchActionPerformed(evt);
			}
		});

		textFieldSelectedFile.setEditable(false);
		//textFieldSelectedFile.setText("No file selected ...");

		javax.swing.GroupLayout tabConvertLayout = new javax.swing.GroupLayout(tabConvert);
		tabConvert.setLayout(tabConvertLayout);
		tabConvertLayout.setHorizontalGroup(
				tabConvertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(tabConvertLayout.createSequentialGroup()
								.addGap(17, 17, 17)
								.addGroup(tabConvertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(buttonChooseFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(tabConvertLayout.createSequentialGroup()
												.addComponent(buttonConvert, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(labelProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(buttonChooseFile, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldSelectedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(tabConvertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabConvertLayout.createSequentialGroup()
												.addComponent(labelQuality)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(sliderQuality, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(labelQualityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabConvertLayout.createSequentialGroup()
												.addGroup(tabConvertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(labelQualityValue2)
														.addComponent(labelQualityValue1))
												.addGap(18, 18, 18)
												.addGroup(tabConvertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(boxArch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(tabConvertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																.addComponent(boxLimit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(boxProcessCount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
								.addContainerGap(19, Short.MAX_VALUE))
		);
		tabConvertLayout.setVerticalGroup(
				tabConvertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(tabConvertLayout.createSequentialGroup()
								.addGap(16, 16, 16)
								.addGroup(tabConvertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(tabConvertLayout.createSequentialGroup()
												.addGroup(tabConvertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(boxArch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(textFieldSelectedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(tabConvertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(boxLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(labelQualityValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(tabConvertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(boxProcessCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(labelQualityValue2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(12, 12, 12)
												.addComponent(jLabel1)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(tabConvertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(labelQuality, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(labelQualityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(sliderQuality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGroup(tabConvertLayout.createSequentialGroup()
												.addGap(41, 41, 41)
												.addComponent(buttonChooseFile, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(buttonChooseFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(tabConvertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(buttonConvert, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(labelProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(9, Short.MAX_VALUE))
		);

		paneMain.addTab("Converter", tabConvert);

		textAreaLogging.setColumns(20);
		textAreaLogging.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
		textAreaLogging.setRows(5);
		scrollPaneLogging.setViewportView(textAreaLogging);

		javax.swing.GroupLayout tabLoggingLayout = new javax.swing.GroupLayout(tabLogging);
		tabLogging.setLayout(tabLoggingLayout);
		tabLoggingLayout.setHorizontalGroup(
				tabLoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(tabLoggingLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPaneLogging, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
								.addContainerGap())
		);
		tabLoggingLayout.setVerticalGroup(
				tabLoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(tabLoggingLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPaneLogging, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
								.addContainerGap())
		);

		paneMain.addTab("Logging", tabLogging);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(labelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(paneMain)
								.addContainerGap())
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(labelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(paneMain)
								.addContainerGap())
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	public static URL createUrl(final String image) {
		return GuetzliConverter.class.getResource(image);
	}

	private void boxLimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxLimitActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_boxLimitActionPerformed

	private void boxArchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxArchActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_boxArchActionPerformed

	private void sliderQualityPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_sliderQualityPropertyChange

	}//GEN-LAST:event_sliderQualityPropertyChange

	private void sliderQualityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderQualityMouseClicked
		labelQualityValue.setText(String.valueOf(sliderQuality.getValue()));
	}//GEN-LAST:event_sliderQualityMouseClicked

	private void sliderQualityMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderQualityMouseMoved
		labelQualityValue.setText(String.valueOf(sliderQuality.getValue()));
	}//GEN-LAST:event_sliderQualityMouseMoved

	private void sliderQualityMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderQualityMouseDragged
		labelQualityValue.setText(String.valueOf(sliderQuality.getValue()));
	}//GEN-LAST:event_sliderQualityMouseDragged

	private void buttonConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConvertActionPerformed
		if (buttonConvert.isEnabled()) {
			final ImageIcon loadingImage = new ImageIcon(createUrl("/spin.gif"));
			labelProgress.setIcon(loadingImage);
			buttonConvert.setEnabled(false);
			processControl = new ProcessControl();
			processControl.setSelectedFiles(fileList);
			processControl.setLabelProgress(this.labelProgress);
			processControl.setButtonConvert(this.buttonConvert);

			final Object processCount = boxProcessCount.getSelectedItem();
			final int procCount = processCount == null ? 1 : Integer.parseInt(processCount.toString());

			final Object arch = boxArch.getSelectedItem();
			final String archi = arch == null ? "32bit" : arch.toString();

			processControl.initRun(procCount, archi, boxArch.getSelectedIndex(),
					(MemoryAmount) boxLimit.getSelectedItem(), sliderQuality.getValue(),
					textAreaLogging);

			processControl.start();
		}
	}//GEN-LAST:event_buttonConvertActionPerformed

	private void buttonConvertMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonConvertMouseReleased
		ImageIcon Icon = new ImageIcon(createUrl("/btnConvert2.png"));
		buttonConvert.setIcon(Icon);
	}//GEN-LAST:event_buttonConvertMouseReleased

	private void buttonConvertMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonConvertMousePressed
		ImageIcon Icon = new ImageIcon(createUrl("/btnConvert1.png"));
		buttonConvert.setIcon(Icon);
	}//GEN-LAST:event_buttonConvertMousePressed

	private void buttonConvertMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonConvertMouseExited
		ImageIcon Icon = new ImageIcon(createUrl("/btnConvert1.png"));
		buttonConvert.setIcon(Icon);
	}//GEN-LAST:event_buttonConvertMouseExited

	private void buttonConvertMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonConvertMouseEntered
		final ImageIcon Icon = new ImageIcon(createUrl("/btnConvert2.png"));
		buttonConvert.setIcon(Icon);
	}//GEN-LAST:event_buttonConvertMouseEntered

	private void buttonConvertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonConvertMouseClicked

	}//GEN-LAST:event_buttonConvertMouseClicked

	private void buttonChooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChooseFileActionPerformed
		final String lastFile = ConfigManager.loadConfig(boxArch, boxLimit, sliderQuality, labelQualityValue, false, boxProcessCount);
		final JFileChooser chooser = new JFileChooser(lastFile);
		final int showOpenDialog = chooser.showOpenDialog(buttonChooseFile);
		if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
			final File choosenFile = chooser.getSelectedFile();
			if (choosenFile.isFile()) {
				fileList.clear();
				fileList.add(choosenFile);
				ConfigManager.saveConfig(choosenFile.getAbsolutePath(), boxArch.getSelectedIndex(),
						boxLimit.getSelectedIndex(), sliderQuality.getValue(), boxProcessCount.getSelectedIndex());
				final String destinationFileName = choosenFile.getName().replaceFirst("[.][^.]+$", "");
				textFieldSelectedFile.setText(choosenFile.getAbsolutePath() + "->" + destinationFileName + "_guetzli.jpg");
				buttonConvert.setEnabled(true);
				convertType = "file";
			}
		}
	}//GEN-LAST:event_buttonChooseFileActionPerformed

	private void buttonChooseFileMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonChooseFileMouseReleased
		ImageIcon I = new ImageIcon(createUrl("/btnChoose2.png"));
		buttonChooseFile.setIcon(I);
	}//GEN-LAST:event_buttonChooseFileMouseReleased

	private void buttonChooseFileMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonChooseFileMousePressed
		ImageIcon I = new ImageIcon(createUrl("/btnChoose1.png"));
		buttonChooseFile.setIcon(I);
	}//GEN-LAST:event_buttonChooseFileMousePressed

	private void buttonChooseFileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonChooseFileMouseExited
		ImageIcon I = new ImageIcon(createUrl("/btnChoose1.png"));
		buttonChooseFile.setIcon(I);
	}//GEN-LAST:event_buttonChooseFileMouseExited

	private void buttonChooseFileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonChooseFileMouseEntered
		ImageIcon I = new ImageIcon(createUrl("/btnChoose2.png"));
		buttonChooseFile.setIcon(I);
	}//GEN-LAST:event_buttonChooseFileMouseEntered

	private void buttonChooseFolderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonChooseFolderMouseEntered
		ImageIcon I = new ImageIcon(createUrl("/btnChooseF2.png"));
		buttonChooseFolder.setIcon(I);
	}//GEN-LAST:event_buttonChooseFolderMouseEntered

	private void buttonChooseFolderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonChooseFolderMouseExited
		ImageIcon I = new ImageIcon(createUrl("/btnChooseF1.png"));
		buttonChooseFolder.setIcon(I);
	}//GEN-LAST:event_buttonChooseFolderMouseExited

	private void buttonChooseFolderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonChooseFolderMousePressed
		ImageIcon I = new ImageIcon(createUrl("/btnChooseF1.png"));
		buttonChooseFolder.setIcon(I);
	}//GEN-LAST:event_buttonChooseFolderMousePressed

	private void buttonChooseFolderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonChooseFolderMouseReleased
		ImageIcon I = new ImageIcon(createUrl("/btnChooseF2.png"));
		buttonChooseFolder.setIcon(I);
	}//GEN-LAST:event_buttonChooseFolderMouseReleased

	private void buttonChooseFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChooseFolderActionPerformed
		String lastFile = ConfigManager.loadConfig(boxArch, boxLimit, sliderQuality, labelQualityValue, false, boxProcessCount);
		JFileChooser chooser = new JFileChooser(lastFile);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			ConfigManager.saveConfig(chooser.getSelectedFile().getAbsolutePath(), boxArch.getSelectedIndex(), boxLimit.getSelectedIndex(), sliderQuality.getValue(), boxProcessCount.getSelectedIndex());
			displayDirectoryContents(chooser.getSelectedFile());
			textFieldSelectedFile.setText(chooser.getSelectedFile().getAbsolutePath() + "\\*->*_guetzli.jpg");
			buttonConvert.setEnabled(true);
			convertType = "folder";
		}
	}//GEN-LAST:event_buttonChooseFolderActionPerformed

	private void boxProcessCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxProcessCountActionPerformed
		// TODO add your handling code here (that's generated.... nothing to do?)
	}//GEN-LAST:event_boxProcessCountActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GuetzliConverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GuetzliConverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GuetzliConverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GuetzliConverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GuetzliConverter().setVisible(true);
			}
		});

	}

	private void setIcon() {
		final URL urlConfig = GuetzliConverter.class.getResource("/icon.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(urlConfig));
		//setIconImage(Toolkit.getDefaultToolkit().getImage(createUrl("/icon.png")));
	}

	private static String processFileExt(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}
	}

	public void displayDirectoryContents(File dir) {
		fileList.clear();
		final File[] files = dir.listFiles();

		if (files == null) {
			return;
		}

		for (final File file : files) {
			if (file.isDirectory()) {
				displayDirectoryContents(file);
			} else {
				if ("jpg".equals(processFileExt(file))
						|| "png".equals(processFileExt(file))
						|| "jpeg".equals(processFileExt(file))) {
					fileList.add(file);
				}
			}
		}
	}


	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JComboBox<String> boxArch;
	private javax.swing.JComboBox<MemoryAmount> boxLimit;
	private javax.swing.JComboBox<String> boxProcessCount;
	private javax.swing.JButton buttonChooseFile;
	private javax.swing.JButton buttonChooseFolder;
	private javax.swing.JButton buttonConvert;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel labelHeader;
	private javax.swing.JLabel labelProgress;
	private javax.swing.JLabel labelQuality;
	private javax.swing.JLabel labelQualityValue;
	private javax.swing.JLabel labelQualityValue1;
	private javax.swing.JLabel labelQualityValue2;
	private javax.swing.JTabbedPane paneMain;
	private javax.swing.JScrollPane scrollPaneLogging;
	private javax.swing.JSlider sliderQuality;
	private javax.swing.JPanel tabConvert;
	private javax.swing.JPanel tabLogging;
	private javax.swing.JTextArea textAreaLogging;
	private javax.swing.JTextField textFieldSelectedFile;
	// End of variables declaration//GEN-END:variables

}
