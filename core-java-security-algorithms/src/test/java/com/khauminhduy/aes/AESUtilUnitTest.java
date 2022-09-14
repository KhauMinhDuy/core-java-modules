package com.khauminhduy.aes;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Paths;

import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class AESUtilUnitTest implements WithAssertions {

	private static final String algorithm = "AES/CBC/PKCS5Padding";

	@Test
	public void givenString_whenEncrypt_thenSuccess() throws Exception {
		String input = "DuyKhau";
		SecretKey secretKey = AESUtils.generateKey(128);
		IvParameterSpec iv = AESUtils.generateIv();

		String cipherText = AESUtils.encrypt(algorithm, input, secretKey, iv);
		String decrypt = AESUtils.decrypt(algorithm, cipherText, secretKey, iv);

		assertEquals(input, decrypt);
	}

	@Test
	public void givenFile_whenEncrypt_thenSuccess() throws Exception {
		SecretKey secretKey = AESUtils.generateKey(128);
		IvParameterSpec iv = AESUtils.generateIv();
		File inputFile = Paths.get("src/test/resources/file.txt").toFile();
		File encryptedFile = new File("dk.encrypted");
		File decryptedFile = new File("document.decrypted");

		AESUtils.encryptFile(algorithm, secretKey, iv, inputFile, encryptedFile);
		AESUtils.decryptFile(algorithm, secretKey, iv, encryptedFile, decryptedFile);

		Assertions.assertThat(inputFile).hasSameTextualContentAs(decryptedFile);
		encryptedFile.delete();
		decryptedFile.delete();

	}

	@Test
	public void givenObject_whenEncrypt_thenSuccess() throws Exception {
		Student student = new Student("DuyKhau", 24);
		SecretKey secretKey = AESUtils.generateKey(128);
		IvParameterSpec iv = AESUtils.generateIv();

		SealedObject sealedObject = AESUtils.encryptObject(algorithm, student, secretKey, iv);
		Student object = (Student) AESUtils.decryptObject(algorithm, sealedObject, secretKey, iv);

		Assertions.assertThat(student).isEqualTo(object);
	}

	@Test
	public void givenPassword_whenEncrypt_thenSuccess() throws Exception {
		String plainText = "DuyKhau";
		String password = "Aa123456!";
		String salt = "12345678";

		IvParameterSpec iv = AESUtils.generateIv();
		SecretKey secretKey = AESUtils.getKeyFromPassword(password, salt);

		String cipherText = AESUtils.encryptPasswordBased(plainText, secretKey, iv);
		String decryptCipherText = AESUtils.decryptPasswordBased(cipherText, secretKey, iv);

		assertEquals(plainText, decryptCipherText);
	}

}
