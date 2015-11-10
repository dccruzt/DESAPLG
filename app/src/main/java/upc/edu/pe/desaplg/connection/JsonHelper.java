package upc.edu.pe.desaplg.connection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Daniela on 23/10/2015.
 */
public class JsonHelper {

    public static JSONObject ConectarTV(){
        try {
            JSONObject json = new JSONObject() {
                {
                    put("accion", "ConectarTV");
                }
            };
            return json;
        } catch (JSONException ex) {
            return null;
        }
    }
}
