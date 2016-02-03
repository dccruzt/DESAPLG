package upc.edu.pe.desaplg.helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Daniela on 28/01/2016.
 */
public class StatusHelper {

    public static JSONArray fichas;
    public static String nombreJugador;
    public static boolean btnJugar_activo = false;

    public static JSONObject puntajes_fichas(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put("A", "1");
                    put("B", "3");
                    put("C", "3");
                    put("CH", "5");
                    put("D", "2");
                    put("E", "1");
                    put("F", "4");
                    put("G", "2");
                    put("H", "4");
                    put("I", "1");
                    put("J", "8");
                    put("K", "8");
                    put("L", "1");
                    put("LL", "8");
                    put("M", "3");
                    put("N", "1");
                    put("Ñ", "8");
                    put("O", "1");
                    put("P", "3");
                    put("Q", "5");
                    put("R", "1");
                    put("RR", "8");
                    put("S", "1");
                    put("T", "1");
                    put("U", "1");
                    put("V", "4");
                    put("W", "8");
                    put("X", "8");
                    put("Y", "4");
                    put("Z", "10");
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject categoria(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put("1", "pais");
                    put("2", "deporte");
                    put("3", "deporte");
                    put("4", "fruta");
                    put("5", "fruta");
                    put("6", "animal");
                    put("7", "animal");
                    put("8", "color");
                    put("9", "color");
                    put("10", "pais");
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }
}