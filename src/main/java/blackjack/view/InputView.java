package blackjack.view;

import blackjack.util.RetryUtil;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public String[] readPlayerName() {
        return RetryUtil.getReturnWithRetry(() -> {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            String playerNames = scanner.nextLine();
            validateNullOrBlank(playerNames);
            return playerNames.split(DELIMITER);
        });
    }

    private void validateNullOrBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalStateException("입력 값이 null이거나 비어있습니다.");
        }
    }

    public String readOneMoreCard(final String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", playerName);
        return scanner.nextLine();
    }

    public int readBettingMoney(final String playerName) {
        System.out.printf("\n%s의 배팅 금액은?\n", playerName);
        String moneyInput = scanner.nextLine();
        validateNumeric(moneyInput);
        return Integer.parseInt(moneyInput);
    }

    private void validateNumeric(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("배팅 금액에는 숫자만 입력할 수 있습니다.");
        }
    }
}
