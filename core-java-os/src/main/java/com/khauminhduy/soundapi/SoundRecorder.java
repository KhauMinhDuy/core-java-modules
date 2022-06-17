package com.khauminhduy.soundapi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class SoundRecorder implements Runnable {

	private AudioFormat format;
	private Thread thread;
	private ByteArrayOutputStream out;
	private AtomicBoolean running = new AtomicBoolean(false);
	private AtomicBoolean stopped = new AtomicBoolean(true);

	public SoundRecorder() throws LineUnavailableException {
		this.format = AudioSystem.getClip().getFormat();
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		running.set(false);
	}

	public void interrupt() {
		running.set(false);
		thread.interrupt();
	}

	public boolean isRunning() {
		return running.get();
	}

	public boolean isStop() {
		return stopped.get();
	}

	@Override
	public void run() {
		running.set(true);
		stopped.set(false);
		if (running.get()) {
			this.out = new ByteArrayOutputStream();
			TargetDataLine line = getTargetDataLineForRecord();
			int frameSizeInBytes = format.getFrameSize();
			if (line == null) {
				return;
			}
			int bufferLengthInFrames = line.getBufferSize() / 8;
			int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
			buildByteOutputStream(this.out, line, bufferLengthInBytes);
		}

		stopped.set(true);
	}

	public ByteArrayOutputStream getOut() {
		return out;
	}

	public static Optional<byte[]> createByteArray(AudioInputStream in) {
		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[1024];
			int len = in.read(buffer, 0, 1024);
			while (len != -1) {
				out.write(buffer, 0, len);
				len = in.read(buffer, 0, 1024);
			}
			return Optional.of(out.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	private void buildByteOutputStream(final ByteArrayOutputStream out, final TargetDataLine line,
			final int bufferLengthInBytes) {
		final byte[] data = new byte[bufferLengthInBytes];
		int numBytesRead;

		line.start();
		while (running.get()) {
			if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
				break;
			}
			out.write(data, 0, numBytesRead);
		}
	}

	private TargetDataLine getTargetDataLineForRecord() {
		TargetDataLine line;
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		if (!AudioSystem.isLineSupported(info)) {
			return null;
		}
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format, line.getBufferSize());
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return line;
	}

}
