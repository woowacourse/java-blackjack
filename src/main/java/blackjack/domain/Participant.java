package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Participant {
    private final List<Card> cards = new ArrayList<>();

    Participant(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
