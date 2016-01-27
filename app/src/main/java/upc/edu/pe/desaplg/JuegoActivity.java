package upc.edu.pe.desaplg;

import android.app.Activity;
import android.os.Bundle;

import upc.edu.pe.desaplg.connection.ConnectionHelper;

/**
 * Created by Daniela on 23/10/2015.
 */
public class JuegoActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_juego);

        ConnectionHelper.desaplgListener.setJuegoActivity(this);
    }
}
