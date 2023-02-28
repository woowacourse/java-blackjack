package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private final List<Card> cards;

    public Participant() {
        cards = new ArrayList<>();
    }


    public void take(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
