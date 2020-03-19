package blackjack.domain.card;

import java.util.Objects;

public class BettingAmount {
    private static double MINIMUM_BETTING_MONEY = 0;
    private static String MINIMUM_BETTING_MONEY_ERR_MSG = "Betting 금액은 %s원 이상입니다.";
    private static String FORMAT_ERR_MSG = "Betting 금액을 숫자로 입력해야 합니다.";

    private double bettingMoney;

    public BettingAmount(double input) {
        if (input < MINIMUM_BETTING_MONEY) {
            throw new IllegalArgumentException(String.format(MINIMUM_BETTING_MONEY_ERR_MSG, MINIMUM_BETTING_MONEY));
        }
        this.bettingMoney = input;
    }

    public BettingAmount(String input) {
        Objects.requireNonNull(validateStringInput(input));
    }

    private static double validateStringInput(String input) {
        Objects.requireNonNull(input);
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(FORMAT_ERR_MSG);
        }
    }
}
