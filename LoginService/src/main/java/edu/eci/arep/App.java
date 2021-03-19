package edu.eci.arep;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.secure;
import static spark.Spark.staticFiles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import edu.eci.arep.login.LoginManager;
import edu.eci.arep.util.CertManager;
import edu.eci.arep.util.Path;
import spark.Request;
import spark.Response;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
            CertificateException, FileNotFoundException, IOException {

        if (args.length == 0) {
            System.out.println("No hay suficientes argumentos");
            System.exit(-1);
        }

        LoginManager.setTIME_SERVER_URL(args[0]);
        port(getPort());
        staticFiles.location("/static");

        CertManager.SetSSlContext("keyscerts/TimeServiceStore", "123456");
        secure("keyscerts/loginkeystore.p12", "123456", null, null);

        get(Path.Web.INDEX, LoginManager::basicPage);
        post(Path.Web.INDEX, LoginManager::processLogin);

        get("*", (Request req, Response res) -> {
            res.redirect(Path.Web.INDEX);
            return null;
        });
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; // returns default port if heroku-port isn't set
    }
}
