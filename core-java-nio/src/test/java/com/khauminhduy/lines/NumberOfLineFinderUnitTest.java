package com.khauminhduy.lines;

import static com.khauminhduy.lines.NumberOfLineFinder.*;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class NumberOfLineFinderUnitTest {

	private static final String INPUT_FILE_NAME = "src/main/resources/input.txt";
	private static final int ACTUAL_LINE_COUNT = 45;
	
	@Test
	public void whenUsingBufferedReader_thenReturnTotalNumberOfLines() throws FileNotFoundException, IOException {
		int lines = getTotalNumberOfLinesUsingBufferedReader(INPUT_FILE_NAME);
		assertEquals(ACTUAL_LINE_COUNT, lines);
	}
	
	@Test
	public void whenUsingLineNumberReader_thenReturnTotalNumberOfLines() throws FileNotFoundException, IOException {
		int lines = getTotalNumberOfLinesUsingLineNumberReader(INPUT_FILE_NAME);
		assertEquals(ACTUAL_LINE_COUNT, lines);
	}
	
	@Test
	public void whenUsingScanner_thenReturnTotalNumberOfLines() throws FileNotFoundException {
		int lines = getTotalNumberOfLinesUsingScanner(INPUT_FILE_NAME);
		assertEquals(ACTUAL_LINE_COUNT, lines);
	}
	
	@Test
	public void whenUsingNIOFiles_thenReturnTotalNumberOfLines() throws IOException {
		int lines = getTotalNumberOfLinesUsingNIOFiles(INPUT_FILE_NAME);
		assertEquals(ACTUAL_LINE_COUNT, lines);
	}
	
	@Test
	public void whenUsingNIOFilesReadAllLines_thenReturnTotalNumberOfLines() throws IOException {
		int lines = getTotalNumberOfLinesUsingNIOFilesReadAllLines(INPUT_FILE_NAME);
		assertEquals(ACTUAL_LINE_COUNT, lines);
	}
	
	@Test
	public void whenUsingNIOFileChannel_thenReturnTotalNumberOfLines() throws IOException {
		int lines = getTotalNumberOfLinesUsingNIOFileChannel(INPUT_FILE_NAME);
		assertEquals(ACTUAL_LINE_COUNT, lines);
	}
	
	@Test
	public void whenUsingApacheCommonsIO_thenReturnTotalNumberOfLines() throws IOException {
		int lines = getTotalNumberOfLinesUsingApacheCommonsIO(INPUT_FILE_NAME);
		assertEquals(ACTUAL_LINE_COUNT, lines);
	}
	
	@Test
	public void whenUsingGoogleGuava_thenReturnTotalNumberOfLines() throws IOException {
		int lines = getTotalNumberOfLinesUsingGoogleGuava(INPUT_FILE_NAME);
		assertEquals(ACTUAL_LINE_COUNT, lines);
	}

}
