package upc.edu.pe.desaplg.connection;

import android.content.Context;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.WebOSTVService;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.sessions.WebAppSession;

/**
 * Created by Daniela on 23/10/2015.
 */
public class ConnectionHelper {

    public static DiscoveryManager discoveryManager;
    public static ConnectableDevice connectableDevice;
    public static WebOSTVService webOSTVService;
    public static WebAppSession webAppSession;
    public static DesaplgListener desaplgListener;
    public static Context context;

    public static void cerrarAplicacion(boolean cerrarWebapp){

        if(cerrarWebapp){

            webAppSession.close(new ResponseListener<Object>() {
                @Override
                public void onSuccess(Object o) {

                }

                @Override
                public void onError(ServiceCommandError serviceCommandError) {

                }
            });

            webAppSession = null;

            if (webAppSession != null)
            {
                webAppSession.disconnectFromWebApp();
                webAppSession = null;
            }
        }

        System.exit(1);
    }
}