package util;

import java.util.Scanner;

public class Console {
    private static final Scanner SCANNER = new Scanner(System.in);

    private Console() {
    }

    public static String nextLine() {
        return SCANNER.nextLine();
    }
}
