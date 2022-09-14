package com.khauminhduy.des;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

public class TripleDESUnitTest {

	@Test
	public void given3DesKey_whenEncryptAndDecryptString_thenCompareResults() throws Exception {
		byte[] secretKey = "9mng65v8jf4lxn93nabf981m".getBytes();
		byte[] iv = "a76nb5h9".getBytes();

		String secretMessage = "DuyKhau secret message";

		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "DESede");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

		byte[] secretMessagesBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
		byte[] encryptedMessageBytes = cipher.doFinal(secretMessagesBytes);

		Cipher decryptCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
		String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);

		assertEquals(secretMessage, decryptedMessage);
	}

}
