package blackjack.domain;

import java.util.List;

public abstract class Participant {
    protected List<Card> cards;

    public Participant() {
    }

    public void distribute(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateTotalValue() {
        return cards.stream()
                .mapToInt(Card::value)
                .sum();
    }
}
