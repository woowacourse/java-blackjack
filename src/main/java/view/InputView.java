package view;

import except.BlackJackException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;
    private final String NAME_SPLITTER = ",";
    private final String INVALID_INPUT = "입력은 공백일 수 없습니다.";
    private final String INVALID_BOOLEAN_INPUT = "y나 n만 입력해주세요";
    private final String NO = "n";
    private final String YES = "y";

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
}
