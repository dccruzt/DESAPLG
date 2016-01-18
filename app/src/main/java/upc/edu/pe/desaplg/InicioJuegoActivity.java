package upc.edu.pe.desaplg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.connectsdk.discovery.DiscoveryManager;

import java.util.Timer;
import java.util.TimerTask;

import upc.edu.pe.desaplg.connection.ConnectionHelper;
import upc.edu.pe.desaplg.connection.DesaplgListener;

/**
 * Created by Daniela on 23/10/2015.
 */
public class InicioJuegoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_iniciojuego);
    }
}