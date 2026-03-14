package domain.participant;

import domain.card.Card;
import domain.status.Start;
import domain.status.Status;

import java.util.List;

abstract public class Participant {

    private final String name;
    protected Status status;

    public Participant(String name) {
        this.name = name;
        this.status = new Start(new HandCards());
    }

    public void drawInitialCards(List<Card> cards) {
        this.status =  status.drawInitialCards(cards);
    }

    public void draw(Card card) {
        this.status = this.status.draw(card);
    }

    public void stay() {
        this.status = this.status.stay();
    }

    public boolean isRunning() {
        return this.status.isRunning();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return this.status.getCards().calculateScore();
    }

    public List<Card> getHandCards() {
        return status.getCards().getCards();
    }

    public Status getStatus() {
        return status;
    }
}
