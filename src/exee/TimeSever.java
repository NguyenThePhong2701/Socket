package exee;

import java.io.*;
import java.net.*;
import java.util.Date;

public class TimeSever {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server thời gian đã được khởi động...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client đã kết nối!");

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String clientMessage;
                while ((clientMessage = in.readLine()) != null) {
                    if (clientMessage.equalsIgnoreCase("time")) {
                        out.println(new Date().toString());
                    } else {
                        out.println("Không hỗ trợ lệnh này!");
                    }
                }
                System.out.println("Kết nối đã đóng...");
            }
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}