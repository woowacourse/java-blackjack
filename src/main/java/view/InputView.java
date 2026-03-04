package view;

import exception.ExceptionMessage;
import java.util.List;
import java.util.Scanner;
import util.Parser;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    private InputView() {
    }

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = readLine();
        return Parser.parseByDelimiter(DELIMITER, input);
    }

    private static String readLine() {
        String input = scanner.nextLine().strip();
        validateInput(input);
        return input;
    }

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }
}
