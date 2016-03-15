package upc.edu.pe.desaplg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

import upc.edu.pe.desaplg.connection.JsonHelper;
import upc.edu.pe.desaplg.helpers.FontHelper;

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

    public void conectar(View v){

        final EditText nombreJugador = (EditText)findViewById(R.id.txtConectar);

        if(nombreJugador.getText().toString().trim().length() == 0 || nombreAvatar.trim().length() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Debe ingresar un nombre y elegir un avatar para conectarse", 2);
            toast.show();
        }
        else {
            ConnectionHelper.webAppSession.sendMessage(JsonHelper.ConectarJugador(nombreJugador.getText().toString(), nombreAvatar),
                    new ResponseListener<Object>() {
                @Override
                public void onSuccess(Object o) {

                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Conectado", 2);
                    toast.show();

                    StatusHelper.nombreJugador = nombreJugador.getText().toString();
                    nombreJugador.setText("");

                    Intent i = new Intent(InicioActivity.this, InicioJuegoActivity.class);
                    startActivity(i);
                }
                @Override
                public void onError(ServiceCommandError serviceCommandError) {
                }
            });
        }
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

        ConnectionHelper.desaplgListener.setInicioActivity(null);
        System.gc();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {}
}