package proxy.checker;

public class MyCheckerRunnable implements Runnable{
    String ip;
    int port;

    public MyCheckerRunnable(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() {
        Main.checkProxy(ip, port);
    }
}
