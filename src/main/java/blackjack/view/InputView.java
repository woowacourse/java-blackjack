package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final InputView INSTANCE = new InputView();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    private InputView() {
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public List<String> readPlayerNames() {
        String input = scanner.nextLine();
        return List.of(splitNames(input));
    }

    private String[] splitNames(String input) {
        return input.split(DELIMITER);
    }

    public String readPlayerIntention() {
        return scanner.nextLine();
    }

    public String readBettingMoney() {
        return scanner.nextLine();
    }
}
