package edu.eci.arep.time;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;

import spark.Request;
import spark.Response;

public class TimeService {

    private static final String USER = "Daniel";
    private static final String PASS = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92"; // 123456

    /**
     * Recibe un usuario y contraseña como parámetro, si estos son correctos,
     * responde la hora del servidor, si son incorrectos, responde un error de
     * autenticación
     * 
     * @param req El request enviado
     * @param res La respuesta por parte del servidor
     * @return El mensaje de respuesta
     * @throws UnsupportedEncodingException Si la codificación del mensaje de
     *                                      respuesta es incorrecta
     */
    public static String getTime(Request req, Response res) throws UnsupportedEncodingException {
        String username = req.queryParams("username");
        String passwd = req.queryParams("password");

        System.out.println("\n\n---------------------------------------------------");
        System.out.println("---------------------------------------------------\n");
        String reqMethod = req.requestMethod();
        System.out.println(reqMethod + " desde la IP: " + req.ip());
        System.out.println(reqMethod + " con URL: " + req.url() + "\n");
        System.out.println("Usuario: " + username);
        System.out.println("Contraseña: " + passwd);

        if (USER.equals(username) && PASS.equals(passwd)) {
            System.out.println("Autenticado Correctamente");
            return new Timestamp(System.currentTimeMillis()).toString();
        } else {
            System.out.println("Error de Autenticación");
            return URLEncoder.encode("Error de Autenticación!!", "UTF-8");
        }
    }
}
