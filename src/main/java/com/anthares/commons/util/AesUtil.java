package com.anthares.commons.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/** Class.
 *
 * @author abelK
 */
public class AesUtil {

  /** Method.
   *
   */
  public static String encrypt(String algorithm, String input, SecretKey key, IvParameterSpec iv)
      throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
      InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    Cipher cipher = Cipher.getInstance(algorithm);
    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
    byte[] cipherText = cipher.doFinal(input.getBytes());
    return Base64.getEncoder()
        .encodeToString(cipherText);
  }

  /** Method.
   *
   */
  public static String decrypt(String algorithm, String cipherText,
      SecretKey key, IvParameterSpec iv)
      throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
      InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    Cipher cipher = Cipher.getInstance(algorithm);
    cipher.init(Cipher.DECRYPT_MODE, key, iv);
    byte[] plainText = cipher.doFinal(Base64.getDecoder()
        .decode(cipherText));
    return new String(plainText);
  }

  /** Method.
   *
   */
  public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(n);
    SecretKey key = keyGenerator.generateKey();
    return key;
  }

  /** Method.
   *
   */
  public static IvParameterSpec generateIv() {
    byte[] iv = new byte[16];
    new SecureRandom().nextBytes(iv);
    return new IvParameterSpec(iv);
  }

  /** Method.
   *
   */
  public static String encryptText(String plainText) {
    String cipherText;
    try {
      SecretKey key = AesUtil.generateKey(128);
      IvParameterSpec ivParameterSpec = AesUtil.generateIv();
      String algorithm = "AES/CBC/PKCS5Padding";
      cipherText = AesUtil.encrypt(algorithm, plainText,
          key, ivParameterSpec);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    } catch (InvalidAlgorithmParameterException e) {
      throw new RuntimeException(e);
    } catch (NoSuchPaddingException e) {
      throw new RuntimeException(e);
    } catch (IllegalBlockSizeException e) {
      throw new RuntimeException(e);
    } catch (BadPaddingException e) {
      throw new RuntimeException(e);
    } catch (InvalidKeyException e) {
      throw new RuntimeException(e);
    }
    return cipherText;
  }

  /** Method.
   *
   */
  public static String decryptCipherText(String cipherText) {
    String plainText;
    try {
      SecretKey key = AesUtil.generateKey(128);
      IvParameterSpec ivParameterSpec = AesUtil.generateIv();
      String algorithm = "AES/CBC/PKCS5Padding";
      plainText = AesUtil.decrypt(algorithm, cipherText, key, ivParameterSpec);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    } catch (InvalidAlgorithmParameterException e) {
      throw new RuntimeException(e);
    } catch (NoSuchPaddingException e) {
      throw new RuntimeException(e);
    } catch (IllegalBlockSizeException e) {
      throw new RuntimeException(e);
    } catch (BadPaddingException e) {
      throw new RuntimeException(e);
    } catch (InvalidKeyException e) {
      throw new RuntimeException(e);
    }
    return plainText;
  }

}
