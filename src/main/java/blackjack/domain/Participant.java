package blackjack.domain;

import java.util.ArrayList;

public abstract class Participant {
    protected Cards cards = new Cards(new ArrayList<>());

    public Participant() {
    }

    public void distribute(Cards cards) {
        this.cards = cards;
    }

    public int calculateTotalValue() {
        return cards.cards().stream()
                .mapToInt(Card::value)
                .sum();
    }

    public abstract boolean isDrawable();

    public void draw(){
        cards.combine(Deck.popOne());
    }
}
