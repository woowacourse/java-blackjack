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

    public boolean isRunning() {
        return false;
    }

    public boolean isBust() {
        return false;
    }

    public boolean isBlackJack() {
        return false;
    }

    public int score() {
        return cards.calculateScore();
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
