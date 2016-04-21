package upc.edu.pe.desaplg.connection;

import android.widget.EditText;

import org.json.JSONArray;
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

    public static JSONObject CargarJuego(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.CARGAR_JUEGO);
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject cargarInicio(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.CARGAR_INICIO);
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject empezarMovimiento(final String letra){

        try {
            final JSONObject ficha = new JSONObject(){
                {
                    put("letra", letra);
                }
            };
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.EMPEZAR_MOVIMIENTO);
                    put(StringsHelper.RESULTADO, ficha);
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject moverFicha(final int desX, final int desY){

        try {
            final JSONObject desplazamiento = new JSONObject(){
                {
                    put("desX", desX);
                    put("desY", desY);
                }
            };
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.MOVER_FICHA);
                    put(StringsHelper.RESULTADO, desplazamiento);
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }
    public static JSONObject soltarFicha(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.SOLTAR_FICHA);
                    put(StringsHelper.RESULTADO, "");
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject jugar(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.JUGAR);
                    put(StringsHelper.RESULTADO, "");
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject retrocederFicha(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.RETROCEDER_FICHA);
                    put(StringsHelper.RESULTADO, "");
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject cambiarFichas(final JSONArray fichas){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.CAMBIAR_FICHAS);
                    put(StringsHelper.RESULTADO, fichas);
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject pasarTurno(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.PASAR_TURNO);
                    put(StringsHelper.RESULTADO, "");
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject mostrarCreditos(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.MOSTRAR_CREDITOS);
                    put(StringsHelper.RESULTADO, "");
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject mostrarCategoria(final String categoria){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.MOSTRAR_CATEGORIA);
                    put(StringsHelper.RESULTADO, categoria);
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject salir(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.SALIR);
                    put(StringsHelper.RESULTADO, "");
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject volverAjugar(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.VOLVER_A_JUGAR);
                    put(StringsHelper.RESULTADO, "");
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }

    public static JSONObject reproducirSonido(){

        try {
            JSONObject json = new JSONObject() {
                {
                    put(StringsHelper.ACCION, StringsHelper.REPRODUCIR_SONIDO);
                    put(StringsHelper.RESULTADO, "");
                }
            };
            return json;

        }catch(JSONException ex){
            return null;
        }
    }
}