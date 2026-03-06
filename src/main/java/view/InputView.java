package view;

import java.util.Scanner;

public final class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public static String readPlayers() {
        return readLine(InfoMessage.PLAYER_INPUT);
    }

    public static String readSelect() {
        return readLine(InfoMessage.SELECT);
    }

    private static String readLine(InfoMessage info) {
        System.out.println(info.message());
        return scanner.nextLine();
    }
}
