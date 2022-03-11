package blackjack.domain.participant;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Status;
import java.util.LinkedHashSet;

public abstract class Participant {

    private final Cards cards;
    private Status status;

    public Participant() {
        this.cards = new Cards(new LinkedHashSet<>());
        this.status = Status.HIT;
    }

    public void init(CardFactory cardFactory) {
        cards.add(cardFactory.drawCard());
        cards.add(cardFactory.drawCard());
    }

    public boolean isHit() {
        return status == Status.HIT;
    }

    public int getScore() {
        return cards.sum();
    }

    public void hit(CardFactory cardFactory) {
        cards.add(cardFactory.drawCard());
        updateStatus();
    }

    protected void updateStatus() {
        if (cards.getStatus() == Status.BUST) {
            status = Status.BUST;
        }
    }

    public void stay() {
        status = Status.STAY;
    }

    public abstract String getName();

    public Cards getCards() {
        return cards;
    }

    public Status getStatus() {
        return status;
    }
}
