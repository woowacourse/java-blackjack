package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Collections;
import java.util.List;

abstract public class Participant {

    protected final Cards cards;

    public Participant(final Cards cards) {
        this.cards = cards;
    }

    public void receiveCard(final Card card) {
        this.cards.add(card);
    }

    public int calculateTotalScore() {
        return this.cards.calculateTotalScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards.getCards());
    }
}
