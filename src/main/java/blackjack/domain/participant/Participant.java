package blackjack.domain.participant;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Status;
import java.util.LinkedHashSet;

public abstract class Participant {

    protected final Cards cards;

    public Participant() {
        this.cards = new Cards(new LinkedHashSet<>());
    }

    public void init(CardFactory cardFactory) {
        cards.add(cardFactory.drawCard());
        cards.add(cardFactory.drawCard());
    }

    public Status getStatus() {
        return cards.getStatus();
    }

    public int getScore() {
        return cards.sum();
    }

    public void hit(CardFactory cardFactory) {
        cards.add(cardFactory.drawCard());
    }
}
