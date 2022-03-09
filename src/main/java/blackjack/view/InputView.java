package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public void terminate() {
        scanner.close();
    }

    public List<String> getPlayerNameInput() {
        String input = scanner.nextLine();
        return Arrays.asList(input.split(","));
    }
}
