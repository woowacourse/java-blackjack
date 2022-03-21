package blackjack.domain.gamer;

import blackjack.domain.result.Match;

public class BettingMoney {
    private static final int MIN = 10;
    private static final int DIVIDED = 10;
    private static final String MIN_ERROR_MESSAGE = MIN + "원 이상의 돈을 입력해주세요.";
    private static final String UNIT_ERROR_MESSAGE = DIVIDED + "원 단위의 금액을 입력해주세요.";

    private final int initialMoney;
    private final int totalMoney;

    private BettingMoney(int initialMoney, int totalMoney) {
        this.initialMoney = initialMoney;
        this.totalMoney = totalMoney;
    }

    public static BettingMoney of(int amount) {
        validate(amount);
        return new BettingMoney(amount, 0);
    }

    public static BettingMoney of(int initialMoney, int totalMoney) {
        return new BettingMoney(initialMoney, totalMoney);
    }

    private static void validate(int amount) {
        validateMin(amount);
        validateDivisible(amount);
    }

    private static void validateMin(int amount) {
        if (amount < MIN) {
            throw new IllegalArgumentException(MIN_ERROR_MESSAGE);
        }
    }

    private static void validateDivisible(int amount) {
        if (amount % DIVIDED != 0) {
            throw new IllegalArgumentException(UNIT_ERROR_MESSAGE);
        }
    }

    public BettingMoney initializeTotalMoney(Match match, int amount) {
        return new BettingMoney(initialMoney, computeTotalMoney(match, amount));
    }

    private int computeTotalMoney(Match match, int amount) {
        if (match.equals(Match.WIN)) {
            return totalMoney + amount;
        }

        if (match.equals(Match.LOSE)) {
            return totalMoney - amount;
        }

        return totalMoney;
    }

    public int getInitialMoney() {
        return initialMoney;
    }

    public int getTotalMoney() {
        return totalMoney;
    }
}
