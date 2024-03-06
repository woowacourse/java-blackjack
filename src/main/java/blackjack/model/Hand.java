package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>(deal());
    }

    private List<Card> deal() {
        return List.of(new Card(), new Card());
    }

    public List<Card> getCards() {
        return cards;
    }
}
