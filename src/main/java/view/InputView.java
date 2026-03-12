package view;

import java.util.Scanner;

public final class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public static String readPlayers() {
        System.out.println(InfoMessage.PLAYER_INPUT.message());
        String names = scanner.nextLine();
        System.out.println();
        return names;
    }

    public static int readBettingAmount(String name) {
        String betting = readByFormatter(InfoMessage.BETTING_AMOUNT.message(), name);
        System.out.println();
        return Integer.parseInt(betting);
    }

    public static String readSelect(String name) {
        return readByFormatter(InfoMessage.SELECT.message(), name);
    }

    private static String readByFormatter(String formatter, String name) {
        System.out.printf(formatter + System.lineSeparator(), name);
        return scanner.nextLine();
    }

    private static String readLine() {
        System.out.println(InfoMessage.PLAYER_INPUT.message());
        return scanner.nextLine();
    }
}
