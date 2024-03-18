package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    public static String getInputFromConsole() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return input;
    }

    public static void print(String output) {
        System.out.print(output);
    }

    public static void printf(String output, Object... objects) {
        System.out.printf(output, objects);
    }

    public static void println(String output) {
        System.out.println(output);
    }
}
