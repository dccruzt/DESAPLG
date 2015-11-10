package upc.edu.pe.desaplg;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.device.DevicePicker;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.WebOSTVService;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.sessions.WebAppSession;

import java.sql.Connection;

import upc.edu.pe.desaplg.adapter.TVAdapter;
import upc.edu.pe.desaplg.connection.ConnectionHelper;
import upc.edu.pe.desaplg.connection.DesaplgListener;
import upc.edu.pe.desaplg.connection.JsonHelper;

public class ConexionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)        ;
        setContentView(R.layout.layout_conexion);

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

                }catch(Exception e){

                }
            }
        });
    }

    public void ConectarJugadorATV(){

        ConnectionHelper.webAppSession.setWebAppSessionListener(ConnectionHelper.desaplgListener);
        int a;
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
