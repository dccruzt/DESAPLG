package upc.edu.pe.desaplg.connection;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.WebOSTVService;
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
}