package view;

import java.util.Scanner;

public final class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public static String readPlayers() {
        String names = readLine(InfoMessage.PLAYER_INPUT);
        System.out.println();
        return names;
    }

    public static String readSelect() {
        return readLine(InfoMessage.SELECT);
    }

    private static String readLine(InfoMessage info) {
        System.out.println(info.message());
        return scanner.nextLine();
    }
}
