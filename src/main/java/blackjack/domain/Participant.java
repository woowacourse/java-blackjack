package blackjack.domain;

import java.util.HashSet;
import java.util.Set;

public class Participant {

    private final Set<Card> cards;

    public Participant() {
        this.cards = new HashSet<>();
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public Set<Card> getCards() {
        return cards;
    }
}
