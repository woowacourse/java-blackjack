package domain;

import java.util.regex.Pattern;

public class BetAmount {

    private static final int MIN_BETTING_AMOUNT = 10000;
    private static final int DRAW_AMOUNT = 0;
    private static final double BLACK_JACK_RATE = 1.5;

    private final int betAmount;

    public BetAmount(String value) {
        validate(value);
        this.betAmount = Integer.parseInt(value);
    }

    private void validate(String value) {
        validateDigit(value);
        validateMinBettingAmount(value);
    }

    private void validateDigit(String value) {
        if (!Pattern.matches("\\d+", value)) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해주세요.");
        }
    }

    private void validateMinBettingAmount(String value) {
        if (Integer.parseInt(value) < MIN_BETTING_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 10000원 이상 베팅해주세요.");
        }
    }

    public int winAmount() {
        return betAmount;
    }

    public int loseAmount() {
        return -betAmount;
    }

    public int drawAmount() {
        return DRAW_AMOUNT;
    }

    public int blackJackAmount() {
        return (int) (betAmount * BLACK_JACK_RATE);
    }
}
