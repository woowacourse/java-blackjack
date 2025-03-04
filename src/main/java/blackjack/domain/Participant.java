package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participant {

    private final List<Card> cards;

    public Participant() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
