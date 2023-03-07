package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

abstract public class Participant {

    protected final Cards cards;

    public Participant(final Cards cards) {
        this.cards = cards;
    }

    final public void receiveCard(final Card card) {
        this.cards.add(card);
    }

    final public int calculateTotalScore() {
        return this.cards.calculateTotalScore();
    }

    final public List<Card> getCards() {
        return cards.getCards();
    }
}
