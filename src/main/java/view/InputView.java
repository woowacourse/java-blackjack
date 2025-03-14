package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String NAME_SPLITTER = ",";
    private static final String INVALID_INPUT = "입력은 공백일 수 없습니다.";
    private static final String INVALID_NUMBER_INPUT = "숫자를 입력해주세요.";
    private static final String INVALID_BOOLEAN_INPUT = "y나 n만 입력해주세요";
    private static final String NO = "n";
    private static final String YES = "y";
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> inputNames() {
        String input = scanner.nextLine();
        validateInput(input);
        return Arrays.stream(input.split(NAME_SPLITTER))
                .map(String::trim)
                .toList();
    }

    private void validateInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }

    public int inputNumber() {
        String input = scanner.nextLine();
        validateInput(input);
        return parseInt(input);
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_INPUT);
        }
    }


    public boolean askDraw() {
        String input = scanner.nextLine();
        validateInput(input);
        validateBoolean(input);
        return input.equals(YES);
    }

    private void validateBoolean(String input) {
        if (input.equals(YES) || input.equals(NO)) {
            return;
        }
        throw new IllegalArgumentException(INVALID_BOOLEAN_INPUT);
    }
}
