package domain.participant;

import domain.card.Card;
import domain.card.HandCards;
import java.util.List;

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

    public List<Card> getCards() {
        return handCards.getCards();
    }
}
