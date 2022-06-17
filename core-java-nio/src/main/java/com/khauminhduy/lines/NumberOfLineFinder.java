package com.khauminhduy.lines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class NumberOfLineFinder {

	public static int getTotalNumberOfLinesUsingBufferedReader(String fileName)
			throws FileNotFoundException, IOException {
		int lines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
			while (reader.readLine() != null) {
				lines++;
			}
		}
		return lines;
	}

	public static int getTotalNumberOfLinesUsingLineNumberReader(String fileName)
			throws FileNotFoundException, IOException {
		int lines = 0;
		try (LineNumberReader reader = new LineNumberReader(new FileReader(fileName));) {
			reader.skip(Integer.MAX_VALUE);
			lines = reader.getLineNumber() + 1;
		}
		return lines;
	}

	public static int getTotalNumberOfLinesUsingScanner(String fileName) throws FileNotFoundException {
		int lines = 0;
		try (Scanner scanner = new Scanner(new FileReader(fileName));) {
			while (scanner.hasNextLine()) {
				scanner.nextLine();
				lines++;
			}
		}
		return lines;
	}

	public static int getTotalNumberOfLinesUsingNIOFiles(String fileName) throws IOException {
		int lines = 0;
		try (Stream<String> fileStream = Files.lines(Paths.get(fileName));) {
			lines = (int) fileStream.count();
		}
		return lines;
	}

	public static int getTotalNumberOfLinesUsingNIOFilesReadAllLines(String fileName) throws IOException {
		int lines = 0;
		List<String> fileStream = Files.readAllLines(Paths.get(fileName));
		lines = fileStream.size();
		return lines;
	}

	public static int getTotalNumberOfLinesUsingNIOFileChannel(String fileName) throws IOException {
		int lines = 1;
		try (FileChannel channel = FileChannel.open(Paths.get(fileName), StandardOpenOption.READ);) {
			ByteBuffer byteBuffer = channel.map(MapMode.READ_ONLY, 0, channel.size());
			while (byteBuffer.hasRemaining()) {
				byte currentChar = byteBuffer.get();
				if (currentChar == '\n') {
					lines++;
				}
			}
		}
		return lines;
	}

	public static int getTotalNumberOfLinesUsingApacheCommonsIO(String fileName) throws IOException {
		int lines = 0;
		LineIterator lineIterator = FileUtils.lineIterator(new File(fileName));
		while (lineIterator.hasNext()) {
			lineIterator.next();
			lines++;
		}
		return lines;
	}

	public static int getTotalNumberOfLinesUsingGoogleGuava(String fileName) throws IOException {
		int lines = 0;
		List<String> lineItems = com.google.common.io.Files.readLines(new File(fileName), StandardCharsets.UTF_8);
		lines = lineItems.size();
		return lines;
	}

}
