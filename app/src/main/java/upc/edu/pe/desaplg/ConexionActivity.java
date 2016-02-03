package upc.edu.pe.desaplg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.device.DevicePicker;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.WebOSTVService;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.sessions.WebAppSession;
import com.connectsdk.service.sessions.WebAppSessionListener;

import java.sql.Connection;
import java.util.Locale;

import upc.edu.pe.desaplg.adapter.TVAdapter;
import upc.edu.pe.desaplg.connection.ConnectionHelper;
import upc.edu.pe.desaplg.connection.DesaplgListener;
import upc.edu.pe.desaplg.connection.JsonHelper;
import upc.edu.pe.desaplg.helpers.FontHelper;

public class ConexionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_conexion);

        TextView lblConexion = (TextView)findViewById(R.id.txtConexion);
        FontHelper.setFont(this.getApplicationContext(), FontHelper.DOSIS_EXTRABOLD, lblConexion);

        ConnectionHelper.desaplgListener.setConexionActivity(this);

        getTVList();
    }

    private void getTVList(){
        DevicePicker picker = new DevicePicker(this);
        ListView TVPicker = picker.getListView();
        ListView listTV = (ListView)findViewById(R.id.listTV);
        listTV.setAdapter(new TVAdapter(TVPicker.getAdapter(), this));

        listTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    ConnectionHelper.connectableDevice = (ConnectableDevice)parent.getItemAtPosition(position);
                    ConnectionHelper.webOSTVService = (WebOSTVService)ConnectionHelper.connectableDevice.getServiceByName("webOS TV");
                    ConnectionHelper.webOSTVService.connect();

                    ConnectionHelper.webOSTVService.joinWebApp("desaplg", new WebAppSession.LaunchListener() {
                        @Override
                        public void onSuccess(WebAppSession webAppSession) {
                            ConnectionHelper.webAppSession = webAppSession;
                            ConectarJugadorATV();
                        }

                        @Override
                        public void onError(ServiceCommandError serviceCommandError) {
                            ConnectionHelper.webOSTVService.launchWebApp("desaplg", new WebAppSession.LaunchListener() {
                                @Override
                                public void onSuccess(WebAppSession webAppSession) {
                                    ConnectionHelper.webAppSession = webAppSession;
                                    ConectarJugadorATV();
                                }

                                @Override
                                public void onError(ServiceCommandError serviceCommandError) {

                                }
                            });
                        }
                    });

                    Intent i = new Intent(ConexionActivity.this, CargandoActivity.class);
                    i.putExtra("mensaje", "Conectando dispositivo...");
                    startActivity(i);

                }catch(Exception ex){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("Tie a word");
                    builder.setMessage("No se pudo conectar a la TV. Verifica que se encuentre encendida y vuele a iniciar Tie a word.");
                }
            }
        });
    }

    public void ConectarJugadorATV(){

        ConnectionHelper.webAppSession.setWebAppSessionListener(ConnectionHelper.desaplgListener);
        ConnectionHelper.webAppSession.sendMessage(JsonHelper.ConectarTV(), new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object o) {
            }

            @Override
            public void onError(ServiceCommandError serviceCommandError) {

            }
        });
    }
}