package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String readParticipantsName() {
        return scanner.nextLine();
    }

    public String readDealDecision() {
        return scanner.nextLine();
    }
}
