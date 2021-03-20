package edu.eci.arep;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.secure;
import static spark.Spark.staticFiles;

import edu.eci.arep.time.TimeService;
import edu.eci.arep.util.Path;

/**
 * Clase principal de TimeService, aquí se define la ruta que se va a manejar
 * para recibir las peticiones y la función que manejará la respuesta a la
 * petición
 * 
 * @author Daniel Rincón
 */
public class App {
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/static");

        secure("keyscerts/timekeystore.p12", "123456", null, null);

        get(Path.Web.INDEX, TimeService::getTime);
    }

    /**
     * Busca el pueto de funcionamiento en las variables de entorno del sistema, y
     * si no lo encuentra utiliza el puerto por defecto.
     * 
     * @return El puerto sobre el cuál correrá el servidor.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; // returns default port if heroku-port isn't set
    }
}
