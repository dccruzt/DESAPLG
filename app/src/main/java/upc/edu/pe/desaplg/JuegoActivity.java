package upc.edu.pe.desaplg;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import upc.edu.pe.desaplg.connection.ConnectionHelper;
import upc.edu.pe.desaplg.helpers.StatusHelper;
import upc.edu.pe.desaplg.view.DesaplgEditText;

/**
 * Created by Daniela on 23/10/2015.
 */
public class JuegoActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_juego);

        ConnectionHelper.desaplgListener.setJuegoActivity(this);
        repartirFichas();

        DesaplgEditText nombreJugador = (DesaplgEditText) findViewById(R.id.nombreJugador);
        nombreJugador.setText(StatusHelper.nombreJugador);
    }

    public void repartirFichas() {

        try{

            TextView ficha_1 = (TextView) findViewById(R.id.letra_ficha1);
            TextView ficha_2 = (TextView) findViewById(R.id.letra_ficha2);
            TextView ficha_3 = (TextView) findViewById(R.id.letra_ficha3);
            TextView ficha_4 = (TextView) findViewById(R.id.letra_ficha4);
            TextView ficha_5 = (TextView) findViewById(R.id.letra_ficha5);
            TextView ficha_6 = (TextView) findViewById(R.id.letra_ficha6);
            TextView ficha_7 = (TextView) findViewById(R.id.letra_ficha7);
            TextView ficha_8 = (TextView) findViewById(R.id.letra_ficha8);

            ficha_1.setText(StatusHelper.fichas.getString(0));
            ficha_2.setText(StatusHelper.fichas.getString(1));
            ficha_3.setText(StatusHelper.fichas.getString(2));
            ficha_4.setText(StatusHelper.fichas.getString(3));
            ficha_5.setText(StatusHelper.fichas.getString(4));
            ficha_6.setText(StatusHelper.fichas.getString(5));
            ficha_7.setText(StatusHelper.fichas.getString(6));
            ficha_8.setText(StatusHelper.fichas.getString(7));

        }catch(JSONException e) {

        }
    }
}
