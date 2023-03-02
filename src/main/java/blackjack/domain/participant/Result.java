package blackjack.domain.participant;

import blackjack.domain.card.Suit;

public enum Result {
    WIN("승"), DRAW("무"), LOSE("패"), EMPTY("Null");

    private final String value;

    Result(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
