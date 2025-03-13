package view;

import exception.BlackJackException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private static final String NAME_SPLITTER = ",";
    private static final String INVALID_INPUT = "입력은 공백일 수 없습니다.";
    private static final String INVALID_BOOLEAN_INPUT = "y나 n만 입력해주세요";
    private static final String NO = "n";
    private static final String YES = "y";
    private static final Pattern IS_NUMBER = Pattern.compile("\\d+");
    private static final String INVALID_BET = "유효하지 않은 베팅 금액입니다.";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> inputNames() {
        String input = scanner.nextLine();
        validateInput(input);
        return Arrays.stream(input.split(NAME_SPLITTER))
                .toList();
    }

    private void validateInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new BlackJackException(INVALID_INPUT);
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
        throw new BlackJackException(INVALID_BOOLEAN_INPUT);
    }

    public int inputBet() {
        String input = scanner.nextLine();
        validateNumber(input);
        return Integer.parseInt(input);
    }

    private void validateNumber(String input) {
        if (!IS_NUMBER.matcher(input).matches()) {
            throw new BlackJackException(INVALID_BET);
        }
    }
}