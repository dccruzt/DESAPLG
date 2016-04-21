package upc.edu.pe.desaplg.connection;

import android.content.Intent;
import com.connectsdk.service.sessions.WebAppSession;
import com.connectsdk.service.sessions.WebAppSessionListener;
import upc.edu.pe.desaplg.CargandoActivity;
import upc.edu.pe.desaplg.ConexionActivity;
import upc.edu.pe.desaplg.FinalJuegoActivity;
import upc.edu.pe.desaplg.InicioActivity;
import upc.edu.pe.desaplg.InicioJuegoActivity;
import upc.edu.pe.desaplg.JuegoActivity;
import upc.edu.pe.desaplg.SplashActivity;
import upc.edu.pe.desaplg.helpers.StatusHelper;
import upc.edu.pe.desaplg.helpers.StringsHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Daniela on 23/10/2015.
 */
public class DesaplgListener implements WebAppSessionListener {

    private ConexionActivity conexionActivity;
    private InicioActivity inicioActivity;
    private InicioJuegoActivity inicioJuegoActivity;
    private JuegoActivity juegoActivity;
    private SplashActivity splashActivity;
    private CargandoActivity cargandoActivity;
    private FinalJuegoActivity finalJuegoActivity;

    public InicioActivity getInicioActivity() {
        return inicioActivity;
    }

    public void setInicioActivity(InicioActivity inicioActivity) {
        this.inicioActivity = inicioActivity;
    }

    public InicioJuegoActivity getInicioJuegoActivity() {
        return inicioJuegoActivity;
    }

    public void setInicioJuegoActivity(InicioJuegoActivity inicioJuegoActivity) {
        this.inicioJuegoActivity = inicioJuegoActivity;
    }

    public JuegoActivity getJuegoActivity() {
        return juegoActivity;
    }

    public void setJuegoActivity(JuegoActivity juegoActivity) {
        this.juegoActivity = juegoActivity;
    }

    public SplashActivity getSplashActivity() {
        return splashActivity;
    }

    public void setSplashActivity(SplashActivity splashActivity) {
        this.splashActivity = splashActivity;
    }

    public ConexionActivity getConexionActivity() {
        return conexionActivity;
    }

    public void setConexionActivity(ConexionActivity conexionActivity) {
        this.conexionActivity = conexionActivity;
    }

    public CargandoActivity getCargandoActivity() {
        return cargandoActivity;
    }

    public void setCargandoActivity(CargandoActivity cargandoActivity) {
        this.cargandoActivity = cargandoActivity;
    }

    public FinalJuegoActivity getFinalJuegoActivity() {
        return finalJuegoActivity;
    }

    public void setFinalJuegoActivity(FinalJuegoActivity finalJuegoActivity) {
        this.finalJuegoActivity = finalJuegoActivity;
    }

    @Override
    public void onReceiveMessage(WebAppSession webAppSession, Object o) {

        try{

            JSONObject json = new JSONObject(o.toString());

            if(json.getString("accion").equals(StringsHelper.PUEDE_INICIAR))
                activaBotonIniciar();

            else if(json.getString("accion").equals(StringsHelper.CONEXION_EXITOSA))
                conexionExitosa();

            else if(json.getString("accion").equals(StringsHelper.LIMITE_JUGADORES))
                limiteJugadores();

            else if(json.getString("accion").equals(StringsHelper.CARGAR_INICIO))
                cargarInicio();

            else if(json.getString("accion").equals(StringsHelper.CARGAR_JUEGO))
                cargarJuego(json.getJSONArray("resultado"));

            else if (json.getString("accion").equals(StringsHelper.EMPEZAR_TURNO))
                empezarTurno(json.getBoolean("resultado"));

            else if (json.getString("accion").equals(StringsHelper.POSICION_FICHA))
                posicionFicha(json.getBoolean("resultado"));

            else if (json.getString("accion").equals(StringsHelper.VALIDAR_PALABRA))
                validarPalabra(json.getJSONObject("resultado"));

            else if (json.getString("accion").equals(StringsHelper.TERMINAR_TURNO))
                terminarTurno(json.getJSONArray("resultado"));

            else if (json.getString("accion").equals(StringsHelper.INICIAR_JUEGO))
                iniciarJuego();

            else if (json.getString("accion").equals(StringsHelper.GANADOR_JUEGO))
                ganadorJuego(json.getJSONArray("resultado"));

            else if (json.getString("accion").equals(StringsHelper.CARGAR_NUEVO_INICIO))
                cargarNuevoInicio();

            else if (json.getString("accion").equals(StringsHelper.CERRAR_APLICACION))
                cerrarAplicacion(json.getBoolean("resultado"));

        }catch(JSONException e){

        }
    }

    @Override
    public void onWebAppSessionDisconnect(WebAppSession webAppSession) {

    }

    public void activaBotonIniciar(){

        if(inicioJuegoActivity != null) {
            inicioJuegoActivity.ActivarBotonIniciar();
        }
        StatusHelper.btnJugar_activo = true;
    }

    public void cargarInicio(){

        if(cargandoActivity != null) {
            Intent i = new Intent(cargandoActivity, InicioActivity.class);
            cargandoActivity.startActivity(i);
            cargandoActivity.finish();
        }
    }

    public void cargarNuevoInicio(){

        if(finalJuegoActivity != null) {
            Intent i = new Intent(finalJuegoActivity, InicioActivity.class);
            finalJuegoActivity.startActivity(i);
            finalJuegoActivity.finish();
        }
    }

    public void cargarJuego(JSONArray fichas){

        StatusHelper.fichas = fichas;
        Intent i = new Intent(inicioJuegoActivity, JuegoActivity.class);
        inicioJuegoActivity.startActivity(i);
        inicioJuegoActivity.finish();
    }

    public void empezarTurno(boolean turno){

        StatusHelper.turno = turno;
        if(juegoActivity != null){
            juegoActivity.empezarTurno();
        }
    }

    public void posicionFicha(boolean valida){

        StatusHelper.posicion_valida = valida;
        if(juegoActivity != null){
            juegoActivity.posicionFicha();
        }

    }

    public void validarPalabra(JSONObject resultado) throws JSONException{

        if(juegoActivity != null){
            juegoActivity.validarPalabra(resultado);
        }
    }

    public void terminarTurno(JSONArray nuevasFichas) throws JSONException{

        if(juegoActivity != null){
            juegoActivity.terminarTurno(nuevasFichas);
        }
    }

    public void iniciarJuego(){

        if(juegoActivity != null){
            juegoActivity.iniciarJuego();
        }
    }

    public void ganadorJuego(JSONArray ganadores) throws JSONException{

        String lblFinal = "";
        String nombres = "";

        for (int i = 0; i < ganadores.length(); i++)
            if(i > 0)
                nombres += " Y " + ganadores.getString(i);
            else
                nombres = ganadores.getString(i);


        if(ganadores.length() > 1)
            lblFinal = "¡FELICITACIONES A " + nombres + "!\n¡SON LOS GANADORES DE TIE-A-WORD!";
        else
            lblFinal = "¡FELICITACIONES A " + nombres + "!\n¡ES EL GANADOR DE TIE-A-WORD!";

        Intent i = new Intent(juegoActivity, FinalJuegoActivity.class);
        i.putExtra("mensaje", lblFinal);
        juegoActivity.startActivity(i);
        juegoActivity.finish();
    }

    public void cerrarAplicacion(boolean cerrarWebapp){

        ConnectionHelper.cerrarAplicacion(cerrarWebapp);
    }

    public void conexionExitosa(){

        if(inicioActivity != null)
            inicioActivity.conexionExitosa();
    }

    public void limiteJugadores(){

        if(inicioActivity != null)
            inicioActivity.limiteJugadores();
    }
}