package httpServer;

import httpServer.config.Configuration;
import httpServer.config.ConfigurationManager;
import httpServer.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;


/**
 *
 * Driver Class for Http Server
 *
 * */

public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main (String[]args){
        LOGGER.info("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Using port " + conf.getPort());
        LOGGER.info("Using Webroot " + conf.getWebroot());

        try{
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        }catch(IOException e){
            e.printStackTrace();
            //TODO HANDLE LATER
        }
    }
}
