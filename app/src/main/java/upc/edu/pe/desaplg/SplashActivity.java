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
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);

        ConnectionHelper.context = getApplicationContext();
        ConnectionHelper.desaplgListener = new DesaplgListener();

        DiscoveryManager.init(getApplicationContext());
        ConnectionHelper.discoveryManager = DiscoveryManager.getInstance();
        ConnectionHelper.discoveryManager.start();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, ConexionActivity.class);
                startActivity(i);
                finish();
            }
        };

        ConnectionHelper.desaplgListener.setSplashActivity(this);

        new Timer().schedule(task, 5000);
    }
}