package com.khauminhduy.soundapi;

import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class App {

	public static void main(String[] args) throws Exception {

//		AudioFormat format = buildAudioFormatInstance();
//
//		SoundRecorder soundRecorder = new SoundRecorder();
//		soundRecorder.build(format);
//
//		System.out.println("Start recording ....");
//		soundRecorder.start();
//
//		JLabel labelMessage = new JLabel("wwwwww");
//		labelMessage.setFont(new Font("Dialog", Font.PLAIN, 20));
//		JOptionPane.showMessageDialog(null, labelMessage, "ads", 1);
//		
//		
//		soundRecorder.stop();

//		WaveDataUtil wd = new WaveDataUtil();
//		Thread.sleep(3000);
//		wd.saveToFile("SoundClip", AudioFileFormat.Type.WAVE, soundRecorder.getAudioInputStream());

//		AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
//		DataLine.Info dataInfo = new DataLine.Info(TargetDataLine.class, format);
//		if (!AudioSystem.isLineSupported(dataInfo)) {
//			throw new Exception("not supported");
//		}
//		TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(dataInfo);
//		targetLine.open();
//		targetLine.start();
//		
//
//		
//		Thread audioRecordThread = new Thread() {
//			@Override
//			public void run() {
//				try {
//					AudioInputStream recordingStream = new AudioInputStream(targetLine);
//
////					File outputFile = new File("test1.wav");
////					AudioSystem.write(recordingStream, AudioFileFormat.Type.WAVE, outputFile);
//
////					Path createTempFile = Files.createTempFile("test", "wav");
////					File file = new File(createTempFile.toAbsolutePath().toString());
////					AudioSystem.write(recordingStream, AudioFileFormat.Type.WAVE, file);
//					
//					
//					ByteArrayOutputStream out = new ByteArrayOutputStream();
//					byte[] buffer = new byte[1024];
//					int len = recordingStream.read(buffer, 0, 1024);
//					while (len != -1) {
//						out.write(buffer, 0, len);
//						len = recordingStream.read(buffer, 0, 1024);
//					}
//					
//					String encodeToString = Base64.getEncoder().encodeToString(out.toByteArray());
//					System.out.println(encodeToString);
//					
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//
//			}
//
//		};
//		audioRecordThread.start();
//		
//
//		JLabel labelMessage = new JLabel("wwwwww");
//		labelMessage.setFont(new Font("Dialog", Font.PLAIN, 20));
//		JOptionPane.showMessageDialog(null, labelMessage, "ads", 1);
//
//		
//		targetLine.stop();
//		targetLine.close();
//		audioRecordThread.interrupt();

		SoundRecorder recorder = new SoundRecorder();
		recorder.start();
		
		Thread.sleep(5000);
		
		byte[] byteArray = recorder.getOut().toByteArray();
		System.out.println(byteArray);
		
		String encodeToString = Base64.getEncoder().encodeToString(byteArray);
		System.out.println(encodeToString);
		
		Files.write(Paths.get("logs.txt"), encodeToString.getBytes(), StandardOpenOption.CREATE);
		
		
		recorder.interrupt();

	}

	public static AudioFormat buildAudioFormatInstance() throws LineUnavailableException {
		return AudioSystem.getClip().getFormat();
	}

}
