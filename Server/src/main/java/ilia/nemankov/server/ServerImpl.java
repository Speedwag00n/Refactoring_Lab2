package ilia.nemankov.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl implements Server {

    private ServerSocket socket;
    private boolean isOn;
    private List<RequestThread> requestThreads = new ArrayList<>();

    public void start(int serverPort) {
        try {
            this.socket = new ServerSocket(serverPort);
            this.isOn = true;
        } catch (IOException e) {
            System.out.println("Failed to take port");
        }
    }

    @Override
    public void serve() {
        while (isOn) {
            try {
                Socket clientSocket = socket.accept();
                RequestThread requestThread = new RequestThread(clientSocket);
                requestThread.run();
                requestThreads.add(requestThread);
            } catch (IOException e) {
                System.out.println("Failed to serve requests");
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        try {
            this.socket.close();
            this.isOn = false;

            for (RequestThread requestThread : requestThreads) {
                if (!requestThread.isFinished()) {
                    Thread.sleep(1000);
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to close port");
        }
    }

}
