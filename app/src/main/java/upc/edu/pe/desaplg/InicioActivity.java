package upc.edu.pe.desaplg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

        TextView lblBienvenida = (TextView)findViewById(R.id.lblBienvenida);
        FontHelper.setFont(this.getApplicationContext(), FontHelper.DOSIS_EXTRABOLD, lblBienvenida);

        EditText txtConectar = (EditText)findViewById(R.id.txtConectar);
        FontHelper.setFont(this.getApplicationContext(), FontHelper.DOSIS_EXTRABOLD, txtConectar);

        Button btnConectar = (Button)findViewById(R.id.btnConectar);
        FontHelper.setFont(this.getApplicationContext(), FontHelper.DOSIS_EXTRABOLD, btnConectar);

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

                    nombreJugador.setText("");
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Conectado", 2);
                    toast.show();

                    StatusHelper.nombreJugador = nombreJugador.getText().toString();
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
        findViewById(R.id.Avatar_Hombre_1).setSelected(false);
        findViewById(R.id.Avatar_Hombre_2).setSelected(false);
        findViewById(R.id.Avatar_Mujer_1).setSelected(false);
        findViewById(R.id.Avatar_Mujer_2).setSelected(false);
        image.setSelected(true);
        try {
            nombreAvatar = getIDName(image, R.id.class).trim();
        }catch(Exception ex){
        }
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
}