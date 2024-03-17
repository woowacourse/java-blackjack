package domain.money;

import domain.game.Result;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal value;

    public Money(String inputValue) {
        try {
            BigDecimal bigDecimal = new BigDecimal(inputValue);
            validate(bigDecimal.doubleValue());
            this.value = bigDecimal;
        } catch (NumberFormatException ne) {
            throw new NumberFormatException("양의 실수만 입력하세요.");
        }
    }

    private void validate(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("양의 실수만 입력하세요.");
        }
    }

    public Profit makeProfit(Result result) {
        return new Profit(value.multiply(result.getProfitRate()));
    }

}
