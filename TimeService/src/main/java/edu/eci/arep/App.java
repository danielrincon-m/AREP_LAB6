package edu.eci.arep;

import static spark.Spark.*;

import edu.eci.arep.time.TimeService;
import edu.eci.arep.util.Path;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        port(getPort());
        staticFiles.location("/static");

        secure("keyscerts/timekeystore.p12", "123456", null, null);

        get(Path.Web.INDEX, TimeService::getTime);
    }
    
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; // returns default port if heroku-port isn't set
    }
}
