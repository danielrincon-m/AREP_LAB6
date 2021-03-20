package edu.eci.arep.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Clase que maneja los certificados y los agrega al trusted source
 */
public class CertManager {

    /**
     * Este método recibe como argumento un archivo de certificado y una contraseña,
     * y los utiliza para parametrizar este certificado cómo una fuente confiable
     * para enviar peticiones
     * 
     * @param certFile El archivo del certificado
     * @param passwd   La contraseña para abrir el certificado
     * @throws KeyStoreException        .
     * @throws NoSuchAlgorithmException .
     * @throws CertificateException     .
     * @throws FileNotFoundException    .
     * @throws IOException              .
     * @throws KeyManagementException   .
     */
    public static void SetSSlContext(String certFile, String passwd) throws KeyStoreException, NoSuchAlgorithmException,
            CertificateException, FileNotFoundException, IOException, KeyManagementException {
        // Create a file and a password representation
        File trustStoreFile = new File(certFile);
        char[] trustStorePassword = passwd.toCharArray();

        // Load the trust store, the default type is "pkcs12", the alternative is "jks"
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);

        // Get the singleton instance of the TrustManagerFactory
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

        // Itit the TrustManagerFactory using the truststore object
        tmf.init(trustStore);

        // Set the default global SSLContext so all the connections will use it
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);
        SSLContext.setDefault(sslContext);
    }
}
