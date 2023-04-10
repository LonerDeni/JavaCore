package ClientServerPlus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainClientPlus {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8089;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String resp = in.readLine();
                if (resp != null) {
                    System.out.println(resp);
                    out.println(scanner.nextLine());
                } else {
                    break;
                }

            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}