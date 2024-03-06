package blackjack.domain.card;

import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Card findFirst() {
        return cards.get(0);
    }

    public List<Card> getCards() {
        return cards;
    }
}
