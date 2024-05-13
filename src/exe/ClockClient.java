package exe;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class ClockClient extends JFrame {
    private JLabel timeLabel;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClockClient() {
        setTitle("Đồng hồ");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeLabel = new JLabel();
        add(timeLabel);

        try {
            socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Timer timer = new Timer(1000, e -> {
                updateTime();
                out.println("time");
                try {
                    String serverTime = in.readLine();
                    if (serverTime != null) {
                        timeLabel.setText(serverTime);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            timer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateTime() {
        timeLabel.setText(new Date().toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClockClient().setVisible(true));
    }
}