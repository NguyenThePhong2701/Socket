package exe;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int PORT = 12345;

        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            String serverMessage, clientMessage;

            while (true) {
                // Nhập dữ liệu từ client và gửi tới server
                System.out.print("Client: ");
                clientMessage = userInput.readLine();
                clientOutput.println(clientMessage);

                // Đọc dữ liệu từ server
                serverMessage = serverInput.readLine();
                System.out.println("Server: " + serverMessage);

                if (clientMessage.equalsIgnoreCase("bye")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}