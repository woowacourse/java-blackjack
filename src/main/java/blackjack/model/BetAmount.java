package blackjack.model;

import blackjack.model.user.Player;

public class BetAmount {

    private static final String ERROR_EMPTY_INPUT = "입력값은 공백일 수 없습니다.";
    private static final String ERROR_BET_AMOUNT_NOT_INTEGER = "배팅 금액은 숫자 형태로 입력해야 합니다.";
    private static final String ERROR_BET_AMOUNT_NOT_POSITIVE = "배팅 금액은 0 이상이어야 합니다.";

    private final Player player;
    private final int amount;

    public BetAmount(Player player, String input) {
        validateEmpty(input);
        int amount = convertToInt(input);
        validatePositive(amount);

        this.player = player;
        this.amount = amount;
    }

    private void validateEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ERROR_EMPTY_INPUT);
        }
    }

    private void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ERROR_BET_AMOUNT_NOT_POSITIVE);
        }
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_BET_AMOUNT_NOT_INTEGER);
        }
    }
}
