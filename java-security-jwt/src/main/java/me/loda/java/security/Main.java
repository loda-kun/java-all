package me.loda.java.security;

import java.nio.ByteBuffer;
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
import javax.crypto.spec.GCMParameterSpec;

public class Main {
    public final static SecureRandom secureRandom = new SecureRandom();

    public static void main(String[] args) throws Exception, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        // Initialise random and generate key
        SecureRandom random = SecureRandom.getInstanceStrong();
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, random);
        SecretKey key = keyGen.generateKey();

        String needToEncyptString = "needToEncString";

        // Encrypt
        Cipher c = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] nonce = generateNonce(12); //NEVER REUSE THIS NONCE WITH SAME KEY
        c.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(128, nonce));
        byte[] encValue = c.doFinal(needToEncyptString.getBytes());
        String encrypted = Base64.getUrlEncoder().encodeToString(formatCipherMsg(nonce, encValue));
        byte[] decodedMsg = Base64.getUrlDecoder().decode(encrypted);

        // decrypt
        ByteBuffer byteBuffer = ByteBuffer.wrap(decodedMsg);
        int ivLength = byteBuffer.getInt();
        if (ivLength != 12) { // check input parameter
            throw new IllegalArgumentException("invalid iv length");
        }
        byte[] nonce1 = new byte[ivLength];
        byteBuffer.get(nonce1);
        byte[] encValue1 = new byte[byteBuffer.remaining()];
        byteBuffer.get(encValue1);
        c.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(128, nonce));
        byte[] decValue = c.doFinal(encValue);
       String decryptedString = new String(decValue);
        System.out.println(needToEncyptString);
        System.out.println(decryptedString);
    }

    private static byte[] generateNonce(int nonceLength) {
        byte[] nonce = new byte[nonceLength];
        secureRandom.nextBytes(nonce); //NEVER REUSE THIS NONCE WITH SAME KEY
        return nonce;
    }

    private static byte[] formatCipherMsg(byte[] iv, byte[] encValue) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES + iv.length + encValue.length);
        byteBuffer.putInt(iv.length);
        byteBuffer.put(iv);
        byteBuffer.put(encValue);
        return byteBuffer.array();
    }

}
