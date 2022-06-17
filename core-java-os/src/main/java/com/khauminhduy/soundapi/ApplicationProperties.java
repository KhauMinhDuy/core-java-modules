package com.khauminhduy.soundapi;

import javax.sound.sampled.AudioFormat;

public class ApplicationProperties {

	public static final AudioFormat.Encoding ENCODING = AudioFormat.Encoding.PCM_SIGNED;
	public static final float RATE = 44100.0f;
	public static final int CHANNELS = 1;
	public static final int SAMPLE_SIZE = 16;
	public static final boolean BIG_ENDIAN = true;

}
