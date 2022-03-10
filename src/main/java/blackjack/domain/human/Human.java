package blackjack.domain.human;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Human {

    public abstract String getName();

    public abstract void addCard(final Card card);

    public abstract Cards getCards();

    public abstract boolean isHit();

    public int getPoint() {
        return getCards().getPoint();
    }

    public int getCardSize() {
        return getCards().size();
    }
}