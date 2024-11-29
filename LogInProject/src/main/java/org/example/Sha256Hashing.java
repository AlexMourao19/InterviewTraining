package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Hashing implements PassWordHashing{
    public Sha256Hashing() {
    }
    public String hashPassword(String passWord) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(passWord.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error initializing hash algorithm", e);
        }
    }
}
