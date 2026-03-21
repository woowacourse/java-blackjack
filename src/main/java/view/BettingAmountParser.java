package view;

import domain.betting.BettingAmount;

public class BettingAmountParser {
    private BettingAmountParser() {
    }

    public static BettingAmount parse(String userInput) {
        validateBlank(userInput);
        int amountValue = parseAmountValue(userInput);
        return BettingAmount.of(amountValue);
    }

    private static int parseAmountValue(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("배팅 금액은 숫자만 입력 가능합니다.");
        }
    }

    private static void validateBlank(String userInput) {
        if (userInput == null || userInput.isBlank()) {
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }
    }
}
