package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {
    protected final List<Card> cards;

    public Participant() {
        this.cards = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    public abstract boolean canReceive(int score);
}
