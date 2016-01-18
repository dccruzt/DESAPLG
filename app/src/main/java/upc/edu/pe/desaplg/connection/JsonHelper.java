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
}
