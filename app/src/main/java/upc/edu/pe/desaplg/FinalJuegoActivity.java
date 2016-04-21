package upc.edu.pe.desaplg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;

import upc.edu.pe.desaplg.connection.ConnectionHelper;
import upc.edu.pe.desaplg.connection.JsonHelper;
import upc.edu.pe.desaplg.helpers.StatusHelper;

/**
 * Created by Daniela on 08/04/2016.
 */
public class FinalJuegoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_finaljuego);

        ConnectionHelper.desaplgListener.setFinalJuegoActivity(this);

        String mensaje = getIntent().getExtras().getString("mensaje");
        if (mensaje != null)
            setMensaje(mensaje);
    }

    public void setMensaje(String mensaje) {

        TextView lblFinalJuego = (TextView)findViewById(R.id.lblFinalJuego);
        lblFinalJuego.setText(mensaje);
    }

    public void volverAjugar(View v){

        Log.e("","boton volver a jugar");
        ConnectionHelper.webAppSession.sendMessage(JsonHelper.volverAjugar(), new ResponseListener<Object>() {
            @Override
            public void onError(ServiceCommandError error) {
            }

            @Override
            public void onSuccess(Object object) {

                /*Intent i = new Intent(FinalJuegoActivity.this, InicioActivity.class);
                startActivity(i);
                finish();*/
            }
        });

    }

    public void salir(View v){

        ConnectionHelper.webAppSession.sendMessage(JsonHelper.salir(), new ResponseListener<Object>() {
            @Override
            public void onError(ServiceCommandError error) {
            }

            @Override
            public void onSuccess(Object object) {
            }
        });
    }

    @Override
    protected void onDestroy() {

        Log.e("", "finaaaal");
        ConnectionHelper.desaplgListener.setFinalJuegoActivity(null);
        super.onDestroy();
        StatusHelper.unbindDrawables(findViewById(R.id.layoutFinalJuego));
        System.gc();
    }

    @Override
    public void onBackPressed() {}
}