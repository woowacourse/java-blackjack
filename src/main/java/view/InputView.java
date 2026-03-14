package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String readPlayersName() {
        return scanner.nextLine();
    }

    public String readYesOrNo() {
        return scanner.nextLine();
    }

    public String readBettingMoney() {
        return scanner.nextLine();
    }
}
