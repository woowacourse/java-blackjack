package blackjack.domain.human;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Human {

    public abstract String getName();

    public abstract void addCard(Card card);

    public abstract Cards getCards();

    public abstract boolean isOneMoreCard();

    public int getPoint() {
        return getCards().getPoint();
    }

    public boolean isOverThanMaxPoint(){
        return getCards().isOverThanMaxPoint();
    }
}
