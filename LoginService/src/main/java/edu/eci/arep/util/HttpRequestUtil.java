package edu.eci.arep.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpRequestUtil {
    public static String doGetRequest(String _url, Map<String, String> params) throws IOException {
        _url = addParams(_url, params);
        URL url = new URL(_url);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        // System.out.println(_url + "  -->  " + con.getResponseCode());

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

    private static String addParams(String url, Map<String, String> params) throws UnsupportedEncodingException, IOException {

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

        // if (resultString.equals("?")) {
        //     con.setDoOutput(true);

        //     DataOutputStream out = new DataOutputStream(con.getOutputStream());
        //     out.writeBytes(resultString);
        //     out.flush();
        //     out.close();

        // }
    }
}
