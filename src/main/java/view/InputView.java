package view;

import dto.PlayerNamesRequest;
import java.util.Scanner;

public final class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public static PlayerNamesRequest readPlayers() {
        System.out.println(InfoMessage.PLAYER_INPUT.message());
        return PlayerNamesRequest.from(scanner.nextLine());
    }

    public static String readSelect(String name) {
        System.out.println(InfoMessage.SELECT.messageWith(name));
        return scanner.nextLine();
    }
}
