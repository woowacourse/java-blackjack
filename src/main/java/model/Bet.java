package model;

import exception.BetFormatException;
import exception.BetRangeException;
import utils.StringUtils;

public class Bet {
    public static final double LOWER_BET_BOUND = 100;

    private final double bet;

    public Bet(String input) {
        validate(input);
        this.bet = Double.parseDouble(input);
    }

    public Bet(double input) {
        validateRange(input);
        this.bet = input;
    }

    private void validate(String input) {
        StringUtils.validateString(input);
        validateFormat(input);
        validateRange(Double.parseDouble(input));
    }

    private void validateFormat(String input) {
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new BetFormatException("베팅금액은 실수만 입력 가능합니다.");
        }
    }

    private void validateRange(double input) {
        if (input < LOWER_BET_BOUND) {
            throw new BetRangeException("베팅금액은 100원 이상부터 입력 가능합니다.");
        }
    }

    public Bet multiplyBet(double ratio) {
        return new Bet(bet * ratio);
    }

    public double getBet() {
        return bet;
    }
}
