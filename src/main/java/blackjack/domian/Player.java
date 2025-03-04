package blackjack.domian;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {
    public Set<Card> cards;

    public void send(Card... cards) {
        this.cards = new HashSet<>(List.of(cards));
    }
}
