package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

abstract public class Participant {

    protected final Cards cards;

    public Participant(final Cards cards) {
        this.cards = cards;
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public int calculateTotalScore() {
        return this.cards.calculateTotalScore();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
