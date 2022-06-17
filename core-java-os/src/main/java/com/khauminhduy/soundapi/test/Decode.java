package com.khauminhduy.soundapi.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

public class Decode {

	public static void main(String[] args) {
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get("logs.txt"));
			byte[] decode = Base64.getDecoder().decode(lines.get(0).trim().getBytes());
			AudioFormat audioFormat = AudioSystem.getClip().getFormat();
			ByteArrayInputStream bais = new ByteArrayInputStream(decode);
			AudioInputStream audioInputStream = new AudioInputStream(bais, audioFormat, decode.length);
			AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File("output3.wav"));
			
			
			
//			lines = Files.readAllLines(Paths.get("log2.txt"));
//			decode = Base64.getDecoder().decode(lines.get(0).trim().getBytes());
//			audioFormat = AudioSystem.getClip().getFormat();
//			bais = new ByteArrayInputStream(decode);
//			audioInputStream = new AudioInputStream(bais, audioFormat, decode.length / audioFormat.getFrameSize());
//			AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File("output2.wav"));
		} catch (IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		
	}

}
