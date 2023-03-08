package domain.user;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Score;

public abstract class User {

    protected final Cards cards = new Cards();

    public void hit(final Card card) {
        cards.addCard(card);
    }

    public Cards getCards() {
        return cards;
    }

    abstract public boolean isHittable();

    abstract public boolean isDealer();

    abstract public boolean isPlayer();

    public Score getScore() {
        return cards.getSumOfScores();
    }
}
