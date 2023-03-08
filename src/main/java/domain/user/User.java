package domain.user;

import domain.Card;
import domain.Cards;
import domain.Score;
import java.util.List;

public abstract class User {

    protected final Cards cards = new Cards();

    public void hit(Card card) {
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    abstract public boolean isHittable();

    abstract public boolean isDealer();

    abstract public boolean isPlayer();

    public Score getScore() {
        return cards.getSumOfScores();
    }
}
