package ilia.nemankov;

import ilia.nemankov.server.Server;
import ilia.nemankov.server.ServerImpl;

public class Main {

    public static void main(String[] args) {
        Server server = new ServerImpl();

        int port = 8090;
        String rawPort = System.getenv("CALENDAR_PORT");
        try {
            port = Integer.parseInt(rawPort);
        } catch (NumberFormatException e) {
            System.out.println("Environment variable CALENDAR_PORT specifies invalid port number");
        }

        server.start(port);
        server.serve();
    }

}
