package com.mindtree.bugtracker.util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtil {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String originalPassword = "password";
		String generatedSecuredPasswordHash = generateStorngPasswordHash(originalPassword);
		System.out.println(generatedSecuredPasswordHash);
		boolean matched = validatePassword("password", generatedSecuredPasswordHash);
		System.out.println(matched);

		matched = validatePassword("password1", generatedSecuredPasswordHash);
		System.out.println(matched);
	}

	public static String generateStorngPasswordHash(String password) {

		String result = "";
		int iterations = 1000;
		char[] chars;
		byte[] salt;
		try {
			chars = password.toCharArray();
			salt = getSalt();

			PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = skf.generateSecret(spec).getEncoded();
			result = iterations + ":" + toHex(salt) + ":" + toHex(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			result = null;
		}
		return result;
	}

	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	public static boolean validatePassword(String originalPassword, String storedPassword) {
		String[] parts = storedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);
		byte[] salt;
		byte[] hash;
		int diff = 1;
		try {
			salt = fromHex(parts[1]);
			hash = fromHex(parts[2]);

			PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] testHash = skf.generateSecret(spec).getEncoded();

			diff = hash.length ^ testHash.length;
			for (int i = 0; i < hash.length && i < testHash.length; i++) {
				diff |= hash[i] ^ testHash[i];
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			diff = 1;
		}
		return diff == 0;
	}

	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
}
