package httpServer.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run(){

        try {

            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //TODO IT WOULD READ
            String html = "<html><head><title>Java HTTP Server</title></head><body><h1>Smacznej kawusi ;)</h1></body></html>";
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
