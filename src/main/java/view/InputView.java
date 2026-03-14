package view;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final String COMMA_DELIMITER = ",";
    private static final String BINARY_REGEX = "[yn]";
    private static final String BINARY_Y = "y";

    private final Scanner sc = new Scanner(System.in);

    public List<String> readPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return splitPlayerNames(userInput());
    }

    public int readBettingAmount(String name) {
        while (true) {
            try {
                System.out.printf("%s의 배팅 금액은?%n", name);
                String money = userInput();
                return Integer.parseInt(money);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    public boolean readPlayerToHitUntilValid(String name) {
        while (true) {
            try {
                System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
                return validateBinaryOption(userInput());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    private List<String> splitPlayerNames(String playerNames) {
        List<String> names = List.of(playerNames.split(COMMA_DELIMITER, -1));
        for (String name : names) {
            if (name.trim().isEmpty()) {
                throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
        return names;
    }

    private boolean validateBinaryOption(String userInput) {
        if (!Pattern.matches(BINARY_REGEX, userInput)) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.");
        }

        return userInput.equals(BINARY_Y);
    }

    private String userInput() {
        return sc.nextLine();
    }
}
