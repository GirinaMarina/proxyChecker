package proxy.checker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            try (FileInputStream fis = new FileInputStream("C:\\Users\\User\\Desktop\\Marina_project\\Java\\ip.txt")) {
                int i;
                String result = "";
                while ((i = fis.read()) != -1) if (i == 13) continue;
                else if (i == 10) {
                    String ip = result.split("\t")[0];
                    String port = result.split("\t")[1];
                    System.out.println("IP: " + ip + " PORT: " + port);
                    // checkProxy(ip, Integer.parseInt(port));

                    Thread t1 = new Thread(new MyCheckerRunnable(ip, Integer.parseInt(port)));
                    Thread t2 = new Thread(new MyCheckerRunnable(ip, Integer.parseInt(port)));

                    MyCheckerThread t3 = new MyCheckerThread(ip, Integer.parseInt(port));
                    MyCheckerThread t4 = new MyCheckerThread(ip, Integer.parseInt(port));

                    Thread t5 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            checkProxy(ip, Integer.parseInt(port));
                        }
                    });

                    Thread t6 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            checkProxy(ip, Integer.parseInt(port));
                        }
                    });

                    t1.start();
                    t2.start();

                    t3.start();
                    t4.start();

                    t5.start();
                    t6.start();

                    result = "";
                } else {
                    result += (char) i;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void checkProxy(String ip, int port){
        try {
            Proxy proxy= new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
            URL url = new URL("https://vozhzhaev.ru/test.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(ip + " не работает");
        }
    }

}
