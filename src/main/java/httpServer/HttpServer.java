package httpServer;

import httpServer.config.Configuration;
import httpServer.config.ConfigurationManager;

/**
 *
 * Driver Class for Http Server
 *
 * */

public class HttpServer {

    public static void main (String[]args){
        System.out.println("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using port " + conf.getPort());
        System.out.println("Using Webroot " + conf.getWebroot());
    }
}
