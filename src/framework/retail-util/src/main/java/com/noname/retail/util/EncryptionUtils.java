package com.noname.retail.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;

public class EncryptionUtils {
	private static Cipher m_cipher;
	private static SecretKeySpec m_keySpec;

	/**
	 * Encryption by AES
	 */
	public static String encrypt(String strToEncrypt) {
		try {
			byte[] input = strToEncrypt.getBytes();
			byte[] encryptedByte;
			synchronized (EncryptionUtils.class) {
				getCipher().init(Cipher.ENCRYPT_MODE, m_keySpec);
				encryptedByte = getCipher().doFinal(input);
			}
			byte[] encryptedArray = Base64.encode(encryptedByte);
			return new String(encryptedArray);
		} catch (Exception e) {
			return strToEncrypt;
		}
	}

	/**
	 * DeCryption by AES
	 */
	public static String decrypt(String strToDecrypt) {
		try {
			byte[] input = Base64.decode(strToDecrypt.getBytes());
			byte[] decryptedByte;
			synchronized (EncryptionUtils.class) {
				getCipher().init(Cipher.DECRYPT_MODE, m_keySpec);
				decryptedByte = getCipher().doFinal(input);
			}
			return new String(decryptedByte);
		} catch (Exception e) {
			return strToDecrypt;
		}
	}

	/**
	 * Encryption by MD5
	 */
	public static String encryptMessageDigest(String strToEncrypt) {
		final String DIGEST = "MD5";
		try {
			// Create MessageDigest instance
			MessageDigest md = MessageDigest.getInstance(DIGEST);
			// Add password bytes to digest
			md.update(strToEncrypt.getBytes());
			// Get the hash's bytes
			byte[] str = md.digest();
			// Get complete hashed password in hex format
			return new String(Hex.encode(str));
		} catch (NoSuchAlgorithmException e) {
			return strToEncrypt;
		}
	}


	private static Cipher getCipher() throws Exception {
		if (m_cipher == null) {
			initEncryption();
		}
		return m_cipher;
	}

	private static void initEncryption() throws Exception {
		// Hardcoded key based on pseudo-random numbers
		final byte[] keyBytes = { -1, -9, 81, -13, 24, -71, -82, -127, -27,
				-20, 46, 76, 84, -11, 79, -9 };
		m_keySpec = new SecretKeySpec(keyBytes, "AES");
		m_cipher = Cipher.getInstance(m_keySpec.getAlgorithm());
	}

	public static void main(String[] args) {
		System.out.println(decrypt("Zgg+4Fk9M6EUFzLugdisKQ=="));
	}
}
