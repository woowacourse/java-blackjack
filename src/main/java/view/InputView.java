package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        String line = readLineAndTrim();
        validateIsBlank(line);
        List<String> names = Arrays.stream(line.split("\\s*,\\s*")).toList();
        names.forEach(this::validateIsBlank);
        return names;
    }

    private String readLineAndTrim() {
        return scanner.nextLine().trim();
    }

    private void validateIsBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("공백은 허용되지 않습니다");
        }
    }

    public int readBetAmount() {
        String line = readLineAndTrim();
        validateIsBlank(line);
        validateIsNumeric(line);
        return validateAndParseToInt(line);
    }

    private void validateIsNumeric(String input) {
        if (input.matches("-?\\d+")) {
            return;
        }
        throw new IllegalArgumentException("숫자가 아닌 문자를 입력할 수 없습니다.");
    }

    private int validateAndParseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("배팅 금액은 정수 범위를 초과할 수 없습니다.");
        }
    }

    public boolean readHitOrStand() {
        String input = readLineAndTrim();
        validateIsBlank(input);
        input = input.trim();
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("y 혹은 n만 입력 가능합니다.");
    }
}
