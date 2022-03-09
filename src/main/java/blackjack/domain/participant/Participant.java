package blackjack.domain.participant;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Status;
import java.util.LinkedHashSet;

public abstract class Participant {

    protected final Cards cards;
    protected Status status;

    public Participant() {
        this.cards = new Cards(new LinkedHashSet<>());
        this.status = Status.HIT;
    }

    public void init(CardFactory cardFactory) {
        cards.add(cardFactory.drawCard());
        cards.add(cardFactory.drawCard());
    }

    public Status getStatus() {
        return status;
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

    private void updateStatus() {
        if (cards.getStatus() == Status.BUST) {
            status = Status.BUST;
        }
    }
}
