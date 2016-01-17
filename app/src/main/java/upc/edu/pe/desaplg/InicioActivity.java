package upc.edu.pe.desaplg;

import android.app.Activity;
import android.content.Context;
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

import upc.edu.pe.desaplg.helpers.FontHelper;

import upc.edu.pe.desaplg.connection.ConnectionHelper;

/**
 * Created by Daniela on 23/10/2015.
 */
public class InicioActivity extends Activity{

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
    }

    public void conectar(View v){

        EditText txtConectar = (EditText)findViewById(R.id.txtConectar);

        if(txtConectar.getText().toString().trim().length() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Debe ingresar un nombre para conectarse", 2);
            toast.show();
        }
        else {
            ConnectionHelper.webAppSession.sendMessage(new JSONObject() {
                {
                    try {
                        EditText nombreJugador = (EditText) findViewById(R.id.txtConectar);
                        put("accion", "enviarNombres");
                        put("jugador", nombreJugador.getText());
                        put("avatar", "Avatar_Hombre_1");
                    } catch (JSONException ex) {
                    }
                }
            }, new ResponseListener<Object>() {
                @Override
                public void onSuccess(Object o) {
                    EditText nombreJugador = (EditText) findViewById(R.id.txtConectar);
                    nombreJugador.setText("");
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Conectado", 3);
                    toast.show();
                }

                @Override
                public void onError(ServiceCommandError serviceCommandError) {
                }
            });
        }
    }

    public void cambiarImagen(View image) {

        image.setSelected(!image.isSelected());
    }
}