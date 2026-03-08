package view;

import dto.NameResponse;
import dto.PlayerNamesRequest;
import dto.SelectRequest;
import java.util.Scanner;

public final class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public static PlayerNamesRequest readPlayers() {
        System.out.println(InfoMessage.PLAYER_INPUT.message());
        return PlayerNamesRequest.from(scanner.nextLine());
    }

    public static SelectRequest readSelect(NameResponse response) {
        System.out.println(InfoMessage.SELECT.messageWith(response.name()));
        return SelectRequest.from(scanner.nextLine());
    }
}
