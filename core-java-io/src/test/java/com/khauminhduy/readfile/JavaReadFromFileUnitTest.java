package com.khauminhduy.readfile;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class JavaReadFromFileUnitTest {

	@Test
	public void whenReadWithBufferedReader_thenCorrect() throws IOException {
		final String expected_value = "Hello, world!";

		try (final BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/fileTest.txt"));) {
			String data = reader.readLine();
			assertEquals(expected_value, data);
		}
	}
	
	@Test
	public void givenFileName_whenUsingClassloader_thenFileData() throws IOException {
		String expectedData = "Hello, world!";
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("fileTest.txt").getFile());
		InputStream inputStream = new FileInputStream(file);
		String data = readFromInputStream(inputStream);
		
		assertEquals(expectedData, data.trim());
	}

	@Test
	public void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData() throws IOException {
		String expectedData = "Hello, world!";

		Class<JavaReadFromFileUnitTest> clazz = JavaReadFromFileUnitTest.class;
		InputStream inputStream = clazz.getResourceAsStream("/fileTest.txt");
		String data = readFromInputStream(inputStream);

		assertEquals(expectedData, data.trim());
	}

	private String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder builder = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line).append("\n");
			}
		}
		return builder.toString();
	}

}
