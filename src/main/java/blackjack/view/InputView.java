package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NAME_DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        String input = scanner.nextLine();
        String[] names = input.split(NAME_DELIMITER);
        return Arrays.stream(names).toList();
    }

    public int readBattingAmount() {
        String input = scanner.nextLine();
        validateInteger(input);
        return Integer.parseInt(input);
    }

    private void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 정수가 아닙니다.");
        }
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
