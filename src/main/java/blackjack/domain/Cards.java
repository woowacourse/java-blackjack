package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList(cards);
    }

    public void add(Cards targetCards) {
        cards.addAll(targetCards.cards);
    }
}
