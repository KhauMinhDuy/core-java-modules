package com.khauminhduy.secretkey;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class.getName());
	private static final String CIPHER = "AES";
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String plainText = "22091019330151174.5.1.20220913";

		encrypt(plainText, getRandomKey(CIPHER, 128));
		encrypt(plainText, getRandomKey(CIPHER, 192));
		encrypt(plainText, getRandomKey(CIPHER, 256));

		// encrypt(plainText, getSecureRandomKey(CIPHER, 128));
		// encrypt(plainText, getSecureRandomKey(CIPHER, 192));
		// encrypt(plainText, getSecureRandomKey(CIPHER, 256));

		// encrypt(plainText, getKeyFromKeyGenerator(CIPHER, 128));
		// encrypt(plainText, getKeyFromKeyGenerator(CIPHER, 192));
		// encrypt(plainText, getKeyFromKeyGenerator(CIPHER, 256));
	}

	private static void encrypt(String plainText, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherTextBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
		logger.info(Base64.getEncoder().encodeToString(cipherTextBytes));
	}

	private static Key getRandomKey(String cipher, int keySize) {
		byte[] randomKeyBytes = new byte[keySize / 8];
		Random random = new Random();
		random.nextBytes(randomKeyBytes);
		return new SecretKeySpec("Zq4t7w!z%C*F)J@NcRfUjXn2r5u8x/A?".getBytes(), cipher);
	}

	private static Key getSecureRandomKey(String cipher, int keySize) {
		byte[] secureRandomKeyBytes = new byte[keySize / 8];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(secureRandomKeyBytes);
		return new SecretKeySpec(secureRandomKeyBytes, cipher);
	}

	private static Key getKeyFromKeyGenerator(String cipher, int keySize) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(cipher);
		keyGenerator.init(keySize);		
		return keyGenerator.generateKey();
	}

	private static Key getPasswordBasedKey(String cipher, int keySize, char[] password) throws InvalidKeySpecException, NoSuchAlgorithmException {
		byte[] salt = new byte[100];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(salt);
		PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, 100000, keySize);
		SecretKey secretKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(pbeKeySpec);
		return new SecretKeySpec(secretKey.getEncoded(), cipher);
	}

}
