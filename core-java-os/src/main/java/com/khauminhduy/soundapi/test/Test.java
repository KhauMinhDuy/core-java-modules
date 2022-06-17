package com.khauminhduy.soundapi.test;

import java.util.Map;

import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer.Info;

public class Test {

	public static void main(String[] args) {
		Type[] audioFileTypes = AudioSystem.getAudioFileTypes();
		for(Type type : audioFileTypes) {
			System.out.println(type);
		}
		
		System.out.println();
		
		Info[] mixerInfo = AudioSystem.getMixerInfo();
		for(Info info : mixerInfo) {
			System.out.println(info);
		}
		System.out.println();
		try {
			Clip clip = AudioSystem.getClip();
			int bufferSize = clip.getBufferSize();
			System.out.println("buffersize: " + bufferSize);
			AudioFormat format = clip.getFormat();
			System.out.println(format);
			
			System.out.println();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public static AudioFormat applyPropertiesToFormat(AudioFormat af, Map<String, Object> props) {
        return new AudioFormat(af.getEncoding(), af.getSampleRate(), af.getSampleSizeInBits(), af.getChannels(),
                af.getFrameSize(), af.getFrameRate(), af.isBigEndian(), props);
    }
	
}
