package view;

import java.util.Scanner;

public final class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public static String readPlayers() {
        String names = readLine();
        System.out.println();
        return names;
    }

    public static String readSelect(String name) {
        String formatter = InfoMessage.SELECT.message();
        System.out.printf(formatter + System.lineSeparator(), name);
        return scanner.nextLine();
    }

    private static String readLine() {
        System.out.println(InfoMessage.PLAYER_INPUT.message());
        return scanner.nextLine();
    }
}
