package exe;

import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server đã được khởi động...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client đã kết nối!");

            BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter clientOutput = new PrintWriter(System.out, true);

            String clientMessage, serverMessage;

            while (true) {
                // Đọc dữ liệu từ client
                clientMessage = clientInput.readLine();
                if (clientMessage.equalsIgnoreCase("bye")) {
                    break;
                }
                System.out.println("Client: " + clientMessage);

                // Nhập dữ liệu từ server và gửi tới client
                System.out.print("Server: ");
                serverMessage = serverInput.readLine();
                serverOutput.println(serverMessage);
            }

            System.out.println("Kết nối đã đóng...");
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}