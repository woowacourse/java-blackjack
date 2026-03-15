package domain.status;

import domain.card.Card;
import domain.participant.HandCards;

import java.util.List;

public abstract class Status {
    protected final HandCards cards;

    public Status(HandCards cards) {
        this.cards = cards;
    }

    public abstract Status drawInitialCards(List<Card> cards);

    public abstract Status draw(Card card);

    public abstract Status stay();

    public abstract boolean isRunning();

    public HandCards getCards() {
        return cards;
    }

}
