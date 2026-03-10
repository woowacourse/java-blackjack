package blackjack.view;

import blackjack.common.error.ErrorCode;
import blackjack.util.InputValidator;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> inputPlayerNames() {
        List<String> names = Arrays.stream(
                scanner.nextLine().split(",")
        ).toList();

        InputValidator.validatePlayerNames(names);

        return names;
    }

    public boolean inputMoreCard() {
        String input = scanner.nextLine();

        if ("y".equals(input)) {
            return true;
        }
        if ("n".equals(input)) {
            return false;
        }

        throw new IllegalArgumentException(ErrorCode.INVALID_INPUT.getMessage());
    }

    public void closeScanner() {
        scanner.close();
    }
}
