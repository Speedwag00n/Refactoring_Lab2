package ilia.nemankov.server;

public interface Server {

    void start(int serverPort);

    void serve();

    void stop();

}
