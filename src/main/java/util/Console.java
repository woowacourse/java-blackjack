package util;

import java.util.Scanner;

public class Console {
    private static Scanner scanner;

    private Console() {
    }

    public static String readLine() {
        if (getInstance() == null) {
            scanner = new Scanner(System.in);
        }
        return scanner.nextLine();
    }

    public static void close() {
        scanner.close();
    }

    private static Scanner getInstance() {
        return scanner;
    }
}
