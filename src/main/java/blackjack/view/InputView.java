package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NAME_SPLIT_DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        String input = scanner.nextLine();
        String[] names = input.split(NAME_SPLIT_DELIMITER);
        return Arrays.stream(names)
                .map(String::trim)
                .toList();
    }

    public boolean isReadCommandYes() {
        String input = scanner.nextLine();
        Command command = Command.from(input);
        return command.isYes();
    }

    public int readBetMoney() {
        String input = scanner.nextLine();
        return parseBetMoney(input);
    }

    private int parseBetMoney(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 금액은 숫자로 이루어져야 합니다.");
        }
    }
}
