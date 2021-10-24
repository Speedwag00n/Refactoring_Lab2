package ilia.nemankov.command;

import java.io.*;
import java.net.Socket;

public abstract class ServerCommand extends Command {

    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult();

        Socket clientSocket = null;
        BufferedWriter out = null;
        BufferedReader in = null;

        try {
            clientSocket = new Socket(getHost(), getServerPort());

            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            out.write(buildServerRequest() + "\n");
            out.flush();

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String answer = in.readLine();

            // receive response as status/message
            String[] answerParts = answer.split("/", 2);

            result.setSuccessful(answerParts[0].equals("0"));
            result.setAnswer(answerParts[1]);
        } catch (IOException e) {
            result.setSuccessful(false);
            result.setAnswer("Could not receive server answer");
        } finally {
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

        return result;
    }

    private String getHost() {
        String host = "localhost";
        String rawHost = System.getenv("CALENDAR_HOST");

        if (rawHost != null && !rawHost.isEmpty()) {
            host = rawHost;
        }

        return host;
    }

    private int getServerPort() {
        int port = 8090;
        String rawPort = System.getenv("CALENDAR_PORT");
        try {
            port = Integer.parseInt(rawPort);
        } catch (NumberFormatException e) {
            System.out.println("Environment variable CALENDAR_PORT specifies invalid port number");
        }

        return port;
    }

    public abstract String buildServerRequest();

}
