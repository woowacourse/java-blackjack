package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.result.WinningStatus;
import domain.score.Score;
import domain.status.Status;
import domain.status.initial.Ready;
import java.math.BigDecimal;
import java.util.List;

public abstract class Participant {

    private final Name name;
    protected Status status;

    public Participant(final Name name) {
        this.name = name;
        this.status = new Ready();
    }

    public Participant(final Name name, final Status status) {
        this.name = name;
        this.status = status;
    }

    abstract boolean isHittable();

    public void receiveInitialCards(Cards cards) {
        this.status = this.status.initialDraw(cards);
    }

    public void receiveCard(Card card) {
        this.status = this.status.draw(card);
    }

    public Score calculateScore() {
        return status.calculateScore();
    }

    public boolean isBust() {
        return status.isBust();
    }

    public BigDecimal profitRate() {
        return status.profitWeight();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        Cards cards = status.cards();
        return List.copyOf(cards.getCards());
    }

    public int getScore() {
        return calculateScore().getValue();
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name=" + name.getName() +
                ", status=" + status.toString() +
                '}';
    }
}
