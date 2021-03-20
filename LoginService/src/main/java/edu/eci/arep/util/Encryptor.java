package edu.eci.arep.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase actualmente solo codifica en formato SHA-256, sin embargo se
 * podrían agregar más algoritmos en el futuro
 */
public class Encryptor {
    /**
     * Recibe como parámetro una cadena y la codifica con el algorimo SHA-256
     * 
     * @param password La cadena que se desea codificar
     * @return La cadena codificada
     */
    public static String encryprPasswordSHA256(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encryptor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        byte[] hash = messageDigest.digest(password.getBytes());
        StringBuilder stringBuffer = new StringBuilder();

        for (byte b : hash) {
            stringBuffer.append(String.format("%02x", b));
        }

        return stringBuffer.toString();
    }
}
