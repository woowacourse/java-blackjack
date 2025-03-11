package domain.participant;

import domain.card.Card;
import domain.card.Cards;

public abstract class Participant {
    protected final Cards cards;

    protected Participant(Cards cards) {
        this.cards = cards;
    }

    public Cards getCards() {
        return cards;
    }

    public void addCards(Cards receivedCards) {
        cards.addAll(receivedCards);
    }

    public void addCard(Card receivedCard) {
        cards.add(receivedCard);
    }

    public int calculateCardsSum() {
        return cards.calculateSum();
    }
}
