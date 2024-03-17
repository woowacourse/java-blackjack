package domain.money;

import domain.game.Result;

public class Money {
    private final int value;

    public Money(String inputValue) {
        try {
            int value = Integer.parseInt(inputValue);
            validate(value);
            this.value = value;
        } catch (NumberFormatException ne) {
            throw new NumberFormatException("양의 정수만 입력하세요.");
        }
    }

    private void validate(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("양의 정수만 입력하세요.");
        }
    }

    public Profit makeProfit(Result result) {
        return new Profit(value * result.getProfitRate());
    }

}
