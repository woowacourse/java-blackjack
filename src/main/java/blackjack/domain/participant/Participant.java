package blackjack.domain.participant;

import java.util.LinkedHashSet;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Status;

public abstract class Participant {

    protected final Cards cards;
    protected Status status;

    public Participant() {
        this.cards = new Cards(new LinkedHashSet<>());
        this.status = Status.HIT;
    }

    public void init(CardDeck cardDeck) {
        cards.add(cardDeck.drawCard());
        cards.add(cardDeck.drawCard());
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

    public void hit(CardDeck cardDeck) {
        cards.add(cardDeck.drawCard());
        updateStatus();
    }

    private void updateStatus() {
        if (cards.getStatus() == Status.BUST) {
            status = Status.BUST;
        }
    }

    public abstract String getName();

    public Cards getCards() {
        return cards;
    }
}
