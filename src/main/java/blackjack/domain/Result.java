package blackjack.domain;

import blackjack.domain.participants.Money;

public enum Result {
    BLACKJACK("블랙잭"),
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    Result(final String value) {
        this.value = value;
    }

    public boolean isSameResult(final Result result) {
        return this == result;
    }

    public double calculateRate(final Money money) {
        if (this == BLACKJACK) {
            return money.getValue() * 1.5;
        }
        if (this == WIN) {
            return money.getValue();
        }
        if (this == DRAW) {
            return 0;
        }
        return -money.getValue();
    }

    public String getValue() {
        return value;
    }
}
