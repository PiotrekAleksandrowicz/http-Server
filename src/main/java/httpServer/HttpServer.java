package httpServer;

import httpServer.config.Configuration;
import httpServer.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //TODO IT WOULD READ
            String html = "<html><head><title>Java HTTP Server</title></head><body><h1>Ahoj Aleksander, smacznej kawusi ;)</h1></body></html>";
            //TODO IT WOULD WRITE

            final String CRLF = "\n\r"; // 13 10

            String response =
                            "HTTP/1.1 200 OK" + CRLF + //Status line : HTTP VERSION RESPONSE_CODE ROSPONSE_MESSAGE
                            "CONTENT-LENGTH" + html.getBytes().length + CRLF + //HEADER
                                    CRLF +
                                    html +
                                    CRLF + CRLF;
            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}
