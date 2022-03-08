package blackjack.domain.human;

import blackjack.domain.Card;
import blackjack.domain.Cards;

public abstract class Human {

    public abstract String getName();

    public abstract void addCard(Card card);

    public abstract Cards getCards();

    public int getPoint() {
        return getCards().getPoint();
    }
}