package upc.edu.pe.desaplg.connection;

import com.connectsdk.service.sessions.WebAppSession;
import com.connectsdk.service.sessions.WebAppSessionListener;

import upc.edu.pe.desaplg.ConexionActivity;

/**
 * Created by Daniela on 23/10/2015.
 */
public class DesaplgListener implements WebAppSessionListener {

    private ConexionActivity conexionActivity;

    public ConexionActivity getConexionActivity() {
        return conexionActivity;
    }

    public void setConexionActivity(ConexionActivity conexionActivity) {
        this.conexionActivity = conexionActivity;
    }


    @Override
    public void onReceiveMessage(WebAppSession webAppSession, Object o) {

    }

    @Override
    public void onWebAppSessionDisconnect(WebAppSession webAppSession) {

    }
}