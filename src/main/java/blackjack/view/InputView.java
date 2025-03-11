package blackjack.view;

import blackjack.controller.HitOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerName() {
        while (true) {
            try {
                System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
                String playerNames = scanner.nextLine();
                validateNullOrBlank(playerNames);
                return Arrays.asList(playerNames.split(DELIMITER));
            } catch (Exception e) {
                displayError(e.getMessage());
            }
        }
    }

    private void validateNullOrBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalStateException("입력 값이 null이거나 비어있습니다.");
        }
    }

    public String readOneMoreCard(final String playerName) {
        while (true) {
            try {
                System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", playerName.trim());
                String answer = scanner.nextLine();
                HitOption.from(answer);
                return answer;
            } catch (Exception e) {
                displayError(e.getMessage());
            }
        }
    }

    public void displayError(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
