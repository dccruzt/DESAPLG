package upc.edu.pe.desaplg.connection;

import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import upc.edu.pe.desaplg.R;
import upc.edu.pe.desaplg.helpers.StringsHelper;

/**
 * Created by Daniela on 23/10/2015.
 */
public class JsonHelper {

    public static JSONObject ConectarTV(){
        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.CONECTAR_TV);
                }
            };
            return json;
        } catch (JSONException ex) {
            return null;
        }
    }

    public static JSONObject ConectarJugador(String nombreJugador, String nombreAvatar){
        final String jugador = nombreJugador;
        final String avatar = nombreAvatar;
        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.CONECTAR_JUGADOR);
                    put(StringsHelper.JUGADOR, jugador);
                    put(StringsHelper.AVATAR, avatar);
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject EmpezarJuego(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.EMPEZAR_JUEGO);
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject CargarFichas(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.CARGAR_FICHAS);
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject enviarFicha(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.ENVIAR_FICHA);
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject cargarInicioAct(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.CARGAR_INICIO_ACT);
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }
}