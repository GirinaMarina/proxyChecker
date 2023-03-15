package proxy.checker;


public class MyCheckerThread extends Thread {
    String ip;
    int port;

    public MyCheckerThread(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() {
        Main.checkProxy(ip, port);
    }
}
