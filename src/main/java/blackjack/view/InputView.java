package blackjack.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String readNames() {
        return scanner.nextLine();
    }

    public void printErrorMessage(final IllegalArgumentException errorMessage) {
        System.out.println(errorMessage);
    }
}
