package model.participant;

import model.card.Cards;
import model.card.Card;

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
        if (isBust() && cards.hasSoftAce()) {
            cards.convertSoftAceToHardAce();
        }
    }

    final public int getScore() {
        return cards.calculateScore().getValue();
    }

    final public List<Card> getCards() {
        return cards.getCards();
    }

    abstract public boolean isHit();

    abstract public String getNickname();
}
