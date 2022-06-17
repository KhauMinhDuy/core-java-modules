package com.khauminhduy.creationdate;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Optional;

import org.junit.Test;

public class CreationDateResolverUnitTest {

	private final CreationDateResolver creationDateResolver = new CreationDateResolver();

	@Test
	public void givenFile_whenGettingCreationDateTimeFromBasicAttributes_thenReturnDate() throws IOException {
		final File file = File.createTempFile("createdFile", ".txt");
		final Path path = file.toPath();

		final Instant response = creationDateResolver.resolveCreationTimeWithBasicAttributes(path);
		assertTrue(Instant.now().isAfter(response));
	}
	
	@Test
	public void givenFile_whenGettingCreationDateTimeFromAttribute_thenReturnDate() throws IOException {
		final File file = File.createTempFile("createdFile", ".txt");
    final Path path = file.toPath();

    final Optional<Instant> response = creationDateResolver.resolveCreationTimeWithAttribute(path);
    response.ifPresent(value -> {
    	assertTrue(Instant.now().isAfter(value));
    });
	}

}
