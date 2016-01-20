package upc.edu.pe.desaplg.connection;

import com.connectsdk.service.sessions.WebAppSession;
import com.connectsdk.service.sessions.WebAppSessionListener;

import upc.edu.pe.desaplg.ConexionActivity;
import upc.edu.pe.desaplg.CreditosActivity;
import upc.edu.pe.desaplg.InicioActivity;
import upc.edu.pe.desaplg.InicioJuegoActivity;
import upc.edu.pe.desaplg.JuegoActivity;
import upc.edu.pe.desaplg.SplashActivity;
import upc.edu.pe.desaplg.helpers.StringsHelper;

/**
 * Created by Daniela on 23/10/2015.
 */
public class DesaplgListener implements WebAppSessionListener {

    private ConexionActivity conexionActivity;
    private CreditosActivity creditosActivity;
    private InicioActivity inicioActivity;

    public CreditosActivity getCreditosActivity() {
        return creditosActivity;
    }

    public void setCreditosActivity(CreditosActivity creditosActivity) {
        this.creditosActivity = creditosActivity;
    }

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

    private InicioJuegoActivity inicioJuegoActivity;
    private JuegoActivity juegoActivity;
    private SplashActivity splashActivity;

    public ConexionActivity getConexionActivity() {
        return conexionActivity;
    }

    public void setConexionActivity(ConexionActivity conexionActivity) {
        this.conexionActivity = conexionActivity;
    }


    @Override
    public void onReceiveMessage(WebAppSession webAppSession, Object o) {

        if(o.equals(StringsHelper.PUEDE_INICIAR))
            activaBotonIniciar();

    }

    @Override
    public void onWebAppSessionDisconnect(WebAppSession webAppSession) {

    }

    public void activaBotonIniciar(){
        //findViewById();
    }
}