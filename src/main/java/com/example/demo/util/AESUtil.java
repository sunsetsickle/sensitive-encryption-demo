
package com.example.demo.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESUtil {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    private static String key;
    private static SecretKeySpec keySpec;

    // 使用ThreadLocal复用Cipher实例，避免线程安全问题
    private static final ThreadLocal<Cipher> ENCRYPT_CIPHER_LOCAL = ThreadLocal.withInitial(AESUtil::createCipher);
    private static final ThreadLocal<Cipher> DECRYPT_CIPHER_LOCAL = ThreadLocal.withInitial(AESUtil::createCipher);

    private static Cipher createCipher() {
        try {
            return Cipher.getInstance(ALGORITHM);
        } catch (Exception e) {
            throw new RuntimeException("创建Cipher失败", e);
        } finally {
            ENCRYPT_CIPHER_LOCAL.remove();
            DECRYPT_CIPHER_LOCAL.remove();
        }
    }

    public static void setKey(String key) {
        AESUtil.key = key;
        AESUtil.keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
    }

    // 确保已初始化
    private static void ensureInitialized() {
        if (key == null || keySpec == null) {
            throw new IllegalStateException("AESUtil未初始化，请先调用setKey()设置密钥");
        }
    }

    public static String encrypt(String plainText) {
        if (plainText == null) {
            return null;
        }
        ensureInitialized();
        try {
            Cipher cipher = ENCRYPT_CIPHER_LOCAL.get();
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("AES加密失败", e);
        }
    }

    public static String decrypt(String cipherText) {
        if (cipherText == null) {
            return null;
        }
        ensureInitialized();
        try {
            Cipher cipher = DECRYPT_CIPHER_LOCAL.get();
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("AES解密失败", e);
        }
    }
}
