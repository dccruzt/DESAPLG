package upc.edu.pe.desaplg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

import upc.edu.pe.desaplg.connection.ConnectionHelper;
import upc.edu.pe.desaplg.connection.DesaplgListener;
import upc.edu.pe.desaplg.connection.JsonHelper;
import upc.edu.pe.desaplg.helpers.StatusHelper;
import upc.edu.pe.desaplg.view.DesaplgTextView;

/**
 * Created by Daniela on 23/10/2015.
 */
public class InicioJuegoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_iniciojuego);

        ConnectionHelper.desaplgListener.setInicioJuegoActivity(this);
        findViewById(R.id.btnJugar).setEnabled(StatusHelper.btnJugar_activo);

        DesaplgTextView lblInicio = (DesaplgTextView)findViewById(R.id.lblInicio);
        lblInicio.setText(StatusHelper.nombreJugador + getResources().getString(R.string.inicio));
    }

    public void ActivarBotonIniciar(){

        findViewById(R.id.btnJugar).setEnabled(true);
    }

    public void iniciarJuego(View v){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Conectando", 2);
        toast.show();
        ConnectionHelper.webAppSession.sendMessage(JsonHelper.EmpezarJuego(), new ResponseListener<Object>() {
            @Override
            public void onError(ServiceCommandError error) {

            }

            @Override
            public void onSuccess(Object object) {
                Context context = getApplicationContext();
                Toast toast2 = Toast.makeText(context, "Mostrando Tablero", 2);
                toast2.show();
            }
        });
    }

    public void CargarFichas(){
        Intent i = new Intent(InicioJuegoActivity.this, JuegoActivity.class);
        startActivity(i);
    }
}