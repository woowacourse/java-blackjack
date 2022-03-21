package blackjack_statepattern.participant;

public class BetMoney {

    private final int amount;

    private BetMoney(int inputValue) {
        validateBetMoney(inputValue);
        this.amount = inputValue;
    }

    public static BetMoney of(int value) {
        return new BetMoney(value);
    }

    public static BetMoney of(String text) {
        int value = checkInteger(text);
        return new BetMoney(value);
    }

    private void validateBetMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 음수가 될 수 없습니다.");
        }
    }

    private static int checkInteger(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 숫자로 입력해주세요.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
