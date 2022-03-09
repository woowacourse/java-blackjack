package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int getTotalNumber() {
        return cards.stream().mapToInt(Card::getNumber).sum();
    }

    public void add(Card card) {
        cards.add(card);
    }
}
