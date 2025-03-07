package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;
import java.util.List;

public abstract class Participant {

    protected final CardDeck cardDeck;

    public Participant(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public void setUpCardDeck(Card first, Card second) {
        cardDeck.setUpCards(first, second);
    }

    protected abstract boolean canTakeMoreCard();

    public void takeMoreCard(Card card) {
        cardDeck.takeMore(card);
    }

    public int calculateScore() {
        return cardDeck.calculateScore();
    }

    public List<Card> getCards() {
        return cardDeck.getCards();
    }
}
