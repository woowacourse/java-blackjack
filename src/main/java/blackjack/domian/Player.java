package blackjack.domian;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class Player {
    private final Set<Card> cards;

    public Player(Set<Card> cards) {
        this.cards = cards;
    }

    public void send(Card... cards) {
        this.cards.addAll(Arrays.asList(cards));
    }

    public Set<Card> getCards() {
        return Collections.unmodifiableSet(cards);
    }
}
