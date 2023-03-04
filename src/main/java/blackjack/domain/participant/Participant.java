package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Participant {

    protected final Cards cards;

    public Participant(final Cards cards) {
        this.cards = cards;
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public int calculateTotalScore() {
        return this.cards.calculateScoreForBlackjack();
    }

    public Cards getCards() {
        return cards;
    }
}
