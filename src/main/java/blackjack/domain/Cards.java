package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        shuffle(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
