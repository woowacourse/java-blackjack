package blackjack.domain.human;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.util.Constants;

public abstract class Human {

    public abstract String getName();

    public abstract void addCard(final Card card);

    public abstract Cards getCards();

    public abstract boolean isAbleToHit();

    public int getPoint() {
        return getCards().getPoint();
    }

    public boolean isTwoCard() {
        return getCards().size() == Constants.INIT_CARD_NUMBER;
    }
}