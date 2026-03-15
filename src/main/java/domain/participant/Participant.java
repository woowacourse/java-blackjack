package domain.participant;

import domain.card.Card;
import domain.status.Start;
import domain.status.Status;

import java.util.List;

public abstract class Participant {

    protected Status status;

    public Participant() {
        this.status = new Start(new HandCards());
    }

    public void drawInitialCards(List<Card> cards) {
        this.status = status.drawInitialCards(cards);
    }

    public void draw(Card card) {
        this.status = this.status.draw(card);
    }

    public void stay() {
        this.status = this.status.stay();
    }

    public boolean isBust() {
        return this.status.isBust();
    }

    public boolean isBlackJack() {
        return this.status.isBlackJack();
    }

    public boolean isRunning() {
        return this.status.isRunning();
    }

    public int getScore() {
        return this.status.score();
    }

    public List<Card> getHandCards() {
        return this.status.getCards();
    }
}
