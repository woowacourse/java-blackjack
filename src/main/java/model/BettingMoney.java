package model;

import exception.GameException;
import java.math.BigInteger;

public class BettingMoney {

    private static final String MONEY_IS_NOT_NUMBER = "문자가 아닌 숫자를 입력해주세요.";
    private static final String MONEY_IS_OUT_OF_RANGE = "입력 가능한 범위를 초과한 숫자입니다.";
    private static final String MONEY_IS_NEGATIVE = "돈은 음수일 수 없습니다.";
    private static final String MONEY_IS_ZERO = "0원 이상을 입력해주세요.";

    private final int bettingMoney;

    public BettingMoney(String bettingMoney) {
        int parsed = parse(bettingMoney);
        validate(parsed);
        this.bettingMoney = parsed;
    }

    public int get() {
        return bettingMoney;
    }

    private int parse(String bettingMoney) {
        BigInteger parsed;
        try {
            parsed = new BigInteger(bettingMoney);
        } catch (NumberFormatException e) {
            throw new GameException(MONEY_IS_NOT_NUMBER);
        }
        if (parsed.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            throw new GameException(MONEY_IS_OUT_OF_RANGE);
        }
        return parsed.intValue();
    }

    private void validate(int bettingMoney) {
        validateBettingMoneyIsNotNegative(bettingMoney);
        validateBettingMoneyIsNotZero(bettingMoney);
    }

    private void validateBettingMoneyIsNotNegative(int bettingMoney) {
        if (bettingMoney < 0) {
            throw new GameException(MONEY_IS_NEGATIVE);
        }
    }

    private void validateBettingMoneyIsNotZero(int bettingMoney) {
        if (bettingMoney == 0) {
            throw new GameException(MONEY_IS_ZERO);
        }
    }
}
