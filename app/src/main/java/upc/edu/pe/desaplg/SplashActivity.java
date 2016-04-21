package upc.edu.pe.desaplg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;

import java.util.Timer;
import java.util.TimerTask;

import upc.edu.pe.desaplg.connection.ConnectionHelper;
import upc.edu.pe.desaplg.connection.DesaplgListener;
import upc.edu.pe.desaplg.connection.JsonHelper;
import upc.edu.pe.desaplg.helpers.StatusHelper;

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

        iniciarDiscovery();

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

    public void iniciarDiscovery(){

        DiscoveryManager.init(getApplicationContext());
        ConnectionHelper.discoveryManager = DiscoveryManager.getInstance();
        ConnectionHelper.discoveryManager.start();
    }

    @Override
    protected void onDestroy() {

        Log.e("", "splashhhhhhhhhh");
        ConnectionHelper.desaplgListener.setSplashActivity(null);
        super.onDestroy();
        StatusHelper.unbindDrawables(findViewById(R.id.layoutSplash));
        System.gc();
    }

    @Override
    public void onBackPressed() {}
}