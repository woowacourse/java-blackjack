package model.participant;

import model.card.Cards;
import model.card.Card;

import java.util.List;

public abstract class Participant {

    protected final Cards cards;

    protected Participant() {
        this.cards = new Cards();
    }

    public void addCard(final Card findCard) {
        cards.add(findCard);
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public void applyAceRule() {
        if (isBust() && cards.hasSoftAce()) {
            cards.convertSoftAceToHardAce();
        }
    }

    public int getScore() {
        return cards.calculateScore().getValue();
    }

    abstract public boolean isHit();

    abstract public String getNickname();

    public List<Card> getCards() {
        return cards.getCards();
    }
}
