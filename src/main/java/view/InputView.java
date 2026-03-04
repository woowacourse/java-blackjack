package view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String readPlayers() {
        return readLine(InfoMessage.PLAYER_INPUT);
    }

    public String readSelect() {
        return readLine(InfoMessage.SELECT);
    }

    private String readLine(InfoMessage info) {
        System.out.println(info.message());
        return scanner.nextLine();
    }
}
