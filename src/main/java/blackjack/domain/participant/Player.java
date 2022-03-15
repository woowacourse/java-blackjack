package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public abstract class Player {

    protected Deck cards;
    protected String name;

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public boolean isOverLimit(int limit) {
        return cards.sumPoints() > limit;
    }

    public String getName() {
        return name;
    }

    public Deck getDeck() {
        return cards;
    }

    public abstract boolean isDealer();
}
