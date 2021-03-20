package edu.eci.arep.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Esta clase actualmente nos permite hacer peticiones GET a una URL con una
 * lista de parámetros, se podría extender para que soporte peticiones de otros
 * tipos.
 * 
 * @author Daniel Rincón
 */
public class HttpRequestUtil {
    /**
     * Función que realiza una petición GET.
     * 
     * @param _url   La URL a la cual se desea realizar la petición
     * @param params Los parámetros que se van a enviar en la petición
     * @return La respuesta a la petición
     * @throws IOException .
     */
    public static String doGetRequest(String _url, Map<String, String> params) throws IOException {
        _url = addParams(_url, params);
        URL url = new URL(_url);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        // System.out.println(_url + " --> " + con.getResponseCode());

        InputStream inputStream = con.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(reader);
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return content.toString();
    }

    /**
     * Esta función agrega los parámetros a la URL de la petición
     * 
     * @param url    La URL a modificar
     * @param params Los parámetros que se desean adicionar a la URL
     * @return La URL modificada.
     * @throws UnsupportedEncodingException .
     * @throws IOException                  .
     */
    private static String addParams(String url, Map<String, String> params)
            throws UnsupportedEncodingException, IOException {

        StringBuilder result = new StringBuilder();

        result.append("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = url + result.toString();
        return resultString.length() > url.length() + 1 ? resultString.substring(0, resultString.length() - 1) : url;
    }
}
