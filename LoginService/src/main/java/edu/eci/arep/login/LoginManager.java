package edu.eci.arep.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import edu.eci.arep.util.Encryptor;
import edu.eci.arep.util.HttpRequestUtil;
import edu.eci.arep.util.Path;
import edu.eci.arep.util.ViewUtil;
import spark.Request;
import spark.Response;

/**
 * Clase que responde a las peticiones de Login
 * 
 * @author Daniel Rincón
 */
public class LoginManager {

    private static String TIME_SERVER_URL = "";

    /**
     * Retorna la página web básica de login cuando se recibe una petición GET
     * 
     * @param req La solicitud del cliente
     * @param res La respuesta del servidor
     * @return La página web de Login
     */
    public static String basicPage(Request req, Response res) {
        printRequest(req);
        HashMap<String, Object> model = new HashMap<>();
        return ViewUtil.render(req, model, Path.Template.INDEX);
    }

    /**
     * Maneja la solicitud POST que se realiza cuando se envía la forma de Login,
     * recupera los datos diligenciados, codifica la contraseña, y envía una
     * petición al servidor de TimeService
     * 
     * @param req La petición del cliente
     * @param res La respuesta del servidor
     * @return La página web de login con la respuesta del servicio
     * @throws UnsupportedEncodingException .
     */
    public static String processLogin(Request req, Response res) throws UnsupportedEncodingException {
        printRequest(req);
        HashMap<String, Object> model = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        String username = req.queryParams("username");
        String passwd = req.queryParams("password");
        String encryptedPasswd = Encryptor.encryprPasswordSHA256(passwd);
        String serverResponse = "";

        params.put("username", username);
        params.put("password", encryptedPasswd);

        try {
            serverResponse = HttpRequestUtil.doGetRequest(TIME_SERVER_URL, params);
        } catch (IOException e) {
            e.printStackTrace();
            serverResponse = "Time Server Error :c";
        }
        model.put("res", URLDecoder.decode(serverResponse, "UTF-8"));
        return ViewUtil.render(req, model, Path.Template.INDEX);
    }

    private static void printRequest(Request req) {
        System.out.println("\n\n---------------------------------------------------");
        System.out.println("---------------------------------------------------\n");
        String reqMethod = req.requestMethod();
        System.out.println(reqMethod + " desde la IP: " + req.ip());
        System.out.println(reqMethod + " con URL: " + req.url());
    }

    public static String getTIME_SERVER_URL() {
        return TIME_SERVER_URL;
    }

    public static void setTIME_SERVER_URL(String tIME_SERVER_URL) {
        TIME_SERVER_URL = tIME_SERVER_URL;
    }
}
