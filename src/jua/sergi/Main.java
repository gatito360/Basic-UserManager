package jua.sergi;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public JTextField nombretex;

    public static void main(String[] args) {

        ArrayList<String> nombres = new ArrayList<>();

        File file = new File("db.yml");
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (line.trim().startsWith("- ")) {
                        nombres.add(line.trim().substring(2));
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("Error to read the file: " + e);
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Add User Name: ");
        String usuNombrescanner = scanner.next();
        nombres.add(usuNombrescanner);

        try (PrintStream out = new PrintStream(file)) {
            out.println("Users:");
            out.println("  Names:");
            for (String nombre : nombres) {
                out.println("    - " + nombre);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error to create the file: " + e);
        }
    }
}
