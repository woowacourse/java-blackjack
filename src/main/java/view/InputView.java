package view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_USER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputUserNames() {
        System.out.println(INPUT_USER_NAME_MESSAGE);
        String userInput = scanner.nextLine();
        validateIsNotBlank(userInput);
        return userInput;
    }

    public String inputPlayerWantMoreCard(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n"
            , playerName);

        String userInput = scanner.nextLine();
        validateIsYOrN(userInput);
        return userInput;
    }

    private void validateIsNotBlank(String userInput) {
        if (userInput.isBlank()) {
            throw new IllegalArgumentException("공백이 입력되었습니다.");
        }
    }

    private void validateIsYOrN(String userInput) {
        if (userInput.equalsIgnoreCase("n") ||
            userInput.equalsIgnoreCase("y")) {
            return;
        }
        throw new IllegalArgumentException("입력은 y 혹은 n으로만 가능합니다.");
    }
}
