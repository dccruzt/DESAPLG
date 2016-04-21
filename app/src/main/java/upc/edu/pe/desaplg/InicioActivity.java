package upc.edu.pe.desaplg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;
import java.lang.reflect.Field;
import upc.edu.pe.desaplg.connection.JsonHelper;
import upc.edu.pe.desaplg.connection.ConnectionHelper;
import upc.edu.pe.desaplg.helpers.StatusHelper;

/**
 * Created by Daniela on 23/10/2015.
 */
public class InicioActivity extends Activity{

    String nombreAvatar = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_inicio);

        ConnectionHelper.desaplgListener.setInicioActivity(this);
    }

    public void conectar(View v) {

        final EditText nombreJugador = (EditText) findViewById(R.id.txtConectar);

        if (nombreJugador.getText().toString().trim().length() == 0 || nombreAvatar.trim().length() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Debe ingresar un nombre y elegir un avatar para conectarse", 2);
            toast.show();
        } else {
            ConnectionHelper.webAppSession.sendMessage(JsonHelper.ConectarJugador(nombreJugador.getText().toString(), nombreAvatar),
                    new ResponseListener<Object>() {
                        @Override
                        public void onSuccess(Object o) {

                            StatusHelper.nombreJugador = nombreJugador.getText().toString();
                            nombreJugador.setText("");
                        }

                        @Override
                        public void onError(ServiceCommandError serviceCommandError) {
                        }
                    });
        }
    }

    public void conexionExitosa(){

        StatusHelper.conexionExitosa = true;
        Intent i = new Intent(InicioActivity.this, InicioJuegoActivity.class);
        startActivity(i);
        finish();

    }

    public void limiteJugadores(){

        Toast toast = Toast.makeText(getApplicationContext(), "Usted no se puede conectar porque ha execdido la cantidad de jugadores posibles.", 4);
        toast.show();
    }

    public void cambiarImagen(View image) {

        findViewById(R.id.Avatar_Hombre_1).setBackgroundResource(0);
        findViewById(R.id.Avatar_Hombre_2).setBackgroundResource(0);
        findViewById(R.id.Avatar_Mujer_1).setBackgroundResource(0);
        findViewById(R.id.Avatar_Mujer_2).setBackgroundResource(0);

        try {
            nombreAvatar = getIDName(image, R.id.class).trim();
            String color = "#C0C0C0";

            switch (nombreAvatar){
                case "Avatar_Hombre_1": color = "#42AFA4"; break;
                case "Avatar_Mujer_1": color = "#F9AAC3"; break;
                case "Avatar_Hombre_2": color = "#7DCFD7"; break;
                case "Avatar_Mujer_2": color = "#FAD2F4"; break;
            }

            LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(InicioActivity.this, R.drawable.seleccionado);
            GradientDrawable shape = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.avatar_sel);

            shape.setColor(Color.parseColor(color));
            image.setBackgroundResource(R.drawable.seleccionado);

        }catch(Exception ex){
        }
    }

    public void mostrarCreditos(View v){

        ConnectionHelper.webAppSession.sendMessage(JsonHelper.mostrarCreditos(), new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(ServiceCommandError serviceCommandError) {

            }
        });
    }

    public static String getIDName(View view, Class<?> clazz) throws Exception {

        Integer id = view.getId();
        Field[] ids = clazz.getFields();
        for (int i = 0; i < ids.length; i++) {
            Object val = ids[i].get(null);
            if (val != null && val instanceof Integer
                    && ((Integer) val).intValue() == id.intValue()) {
                return ids[i].getName();
            }
        }
        return "";

    }

    @Override
    protected void onDestroy() {

        Log.e("", "inicioooooooooooooooooo");
        ConnectionHelper.desaplgListener.setInicioActivity(null);
        super.onDestroy();
        StatusHelper.unbindDrawables(findViewById(R.id.layoutInicio));
        System.gc();
    }

    @Override
    public void onBackPressed() {}
}