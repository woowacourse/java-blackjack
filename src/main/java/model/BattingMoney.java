package model;

import exception.GameException;
import java.math.BigInteger;

public class BattingMoney {

    private static final String MONEY_IS_NOT_NUMBER = "문자가 아닌 숫자를 입력해주세요.";
    private static final String MONEY_IS_OUT_OF_RANGE = "입력 가능한 범위를 초과한 숫자입니다.";
    private static final String MONEY_IS_NEGATIVE = "돈은 음수일 수 없습니다.";
    private static final String MONEY_IS_ZERO = "0원 이상을 입력해주세요.";

    private final Integer battingMoney;

    public BattingMoney(String battingMoney) {
        int parsed = parse(battingMoney);
        validate(parsed);
        this.battingMoney = parsed;
    }

    public Integer get() {
        return battingMoney;
    }

    private int parse(String battingMoney) {
        BigInteger parsed;
        try {
            parsed = new BigInteger(battingMoney);
        } catch (NumberFormatException e) {
            throw new GameException(MONEY_IS_NOT_NUMBER);
        }
        if (parsed.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            throw new GameException(MONEY_IS_OUT_OF_RANGE);
        }
        return parsed.intValue();
    }

    private void validate(int battingMoney) {
        validateBattingMoneyIsNotNegative(battingMoney);
        validateBattingMoneyIsNotZero(battingMoney);
    }

    private void validateBattingMoneyIsNotNegative(int battingMoney) {
        if (battingMoney < 0) {
            throw new GameException(MONEY_IS_NEGATIVE);
        }
    }

    private void validateBattingMoneyIsNotZero(int battingMoney) {
        if (battingMoney == 0) {
            throw new GameException(MONEY_IS_ZERO);
        }
    }
}
