package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(Card card) {
        this(Collections.singletonList(card));
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }
}
