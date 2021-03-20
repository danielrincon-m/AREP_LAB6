package edu.eci.arep.util;

import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

/**
 * Clase auxiliar encargada de facilitar la síntaxis para el renderizado de las
 * páginas
 *
 * @author Daniel Rincón
 */
public class ViewUtil {

    /**
     * Renderiza la página web enviada junto con sus datos
     * 
     * @param request      La reques enviada por el cliente
     * @param model        El modelo de datos enviado a la plantilla de la página
     *                     web
     * @param templatePath El path de la plantilla a renderizar
     *
     * @return Retorna la página web renderizada
     */
    public static String render(Request request, Map<String, Object> model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
