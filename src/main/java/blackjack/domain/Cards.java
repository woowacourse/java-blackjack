package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> cards(){
        return Collections.unmodifiableList(cards);
    }

    public void combine(Cards otherCards) {
        this.cards.addAll(otherCards.cards());
    }
}
