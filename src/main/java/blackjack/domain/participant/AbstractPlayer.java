package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public abstract class AbstractPlayer implements Player {

    protected Deck cards;
    protected String name;

    @Override
    public void addCard(Card card) {
        cards.addCard(card);
    }

    @Override
    public boolean isOverLimit(int limit) {
        return cards.sumPoints() > limit;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Deck getDeck() {
        return cards;
    }

    public abstract boolean isDealer();
}
