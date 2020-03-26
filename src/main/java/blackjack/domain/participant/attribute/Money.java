package blackjack.domain.participant.attribute;

import blackjack.domain.result.ResultType;

import java.util.Objects;

public class Money {
    private static final String INVALID_BETTING_MONEY_ERR_MSG = "배팅 금액은 0이하일 수 없습니다.";
    private static final int MINIMUM_BETTING_MONEY = 0;
    public static final String NULL_RESULT_TYPE_ERR_MSG = "결과 타입이 입력되지 않았습니다.";

    private final double bettingMoney;

    public Money(double bettingMoney) {
        if (bettingMoney <= MINIMUM_BETTING_MONEY) {
            throw new IllegalArgumentException(INVALID_BETTING_MONEY_ERR_MSG);
        }
        this.bettingMoney = bettingMoney;
    }

    public double computeProfit(ResultType type) {
        Objects.requireNonNull(type, NULL_RESULT_TYPE_ERR_MSG);
        return bettingMoney * type.getProfitRate();
    }
}

