package blackjack.view;

import java.util.Scanner;

public class InputView {

    static Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayerName() {
        return userInput();
    }

    public static String inputReceiveMoreCardAnswer() {
        return userInput();
    }

    private static String userInput() {
        return scanner.nextLine();
    }
}
