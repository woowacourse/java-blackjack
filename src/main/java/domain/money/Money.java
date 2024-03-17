package domain.money;

import domain.game.Result;

public class Money {
    private final int value;

    public Money(String value) {
        try {
            this.value = Integer.parseInt(value);
        } catch (NumberFormatException ne) {
            throw new NumberFormatException("양의 정수만 입력하세요.");
        }
        validate();
    }

    private void validate() {
        if (value <= 0) {
            throw new NumberFormatException("양의 정수만 입력하세요.");
        }
    }

    public Profit makeProfit(Result result) {
        return new Profit(value * result.getProfitRate());
    }

}
