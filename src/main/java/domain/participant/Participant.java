package domain.participant;

import domain.card.Card;
import domain.status.Start;
import domain.status.Status;
import domain.Score;

import java.util.Arrays;
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

    public boolean isFinished() {
        return this.status.isFinished();
    }

    public String getName() {
        return name;
    }

    // Status에 위임하여 정보 획득
    public Score getScore() {
        return this.status.getCards().getScore();
    }

    public List<Card> getHandCards() {
        return status.getCards().getCards();
    }
}
