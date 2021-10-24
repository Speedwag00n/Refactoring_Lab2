package ilia.nemankov.server;

import ilia.nemankov.command.*;
import lombok.Getter;

import java.io.*;
import java.net.Socket;

public class RequestThread implements Runnable {

    private final Socket clientSocket;
    @Getter
    private boolean finished;

    public RequestThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        BufferedWriter out = null;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String request = in.readLine();

            System.out.println("Received request: " + request);

            String answerMessage;
            boolean isSuccessful = false;

            try {
                CommandHandler commandHandler = new CommandHandlerImpl(new CommandFactoryImpl());
                CommandResult result = commandHandler.execute(request);
                answerMessage = result.getAnswer();
                isSuccessful = true;
            } catch (InvalidCommandException | InvalidCommandArgumentsException e) {
                answerMessage = e.getMessage();
            }

            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            // 0 - finished without problems, 1 - there is some problem
            String statusInfo = isSuccessful ? "0" : "1";

            // send response as status/message
            String response = statusInfo + "/" + answerMessage;
            System.out.println("Response: " + response);

            out.write(response);
            out.flush();
        } catch (IOException e) {
            System.out.println("Failed to process received request");
            e.printStackTrace();
        } finally {
            finished = true;

            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
