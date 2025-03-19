package domain.participant;

import domain.card.Card;
import domain.card.HandCards;

public abstract class Participant {

    protected final HandCards handCards;

    public Participant(HandCards handCards) {
        this.handCards = handCards;
    }

    public void setUpCardDeck(Card first, Card second) {
        handCards.setUpCards(first, second);
    }

    protected abstract boolean canTakeMoreCard();

    public void takeMoreCard(Card card) {
        handCards.takeMore(card);
    }

    public int calculateScore() {
        return handCards.calculateScore();
    }

    public int getCardCount() {
        return handCards.getCardCount();
    }

    public HandCards getHands() {
        return handCards;
    }
}
