package participant;

import card.Cards;
import card.Card;
import score.Score;

import java.util.List;

public abstract class Participant {

    protected final Cards cards;

    protected Participant() {
        this.cards = new Cards();
    }

    final public void addCard(final Card findCard) {
        cards.add(findCard);
    }

    final public boolean isBust() {
        return cards.isBust();
    }

    final public void applyAceRule() {
        cards.applyAceRule();
    }

    final public Score getScore() {
        return cards.calculateScore();
    }

    final public List<Card> getCards() {
        return cards.getCards();
    }

    abstract public boolean canHit();

    abstract public String getNickname();
}
