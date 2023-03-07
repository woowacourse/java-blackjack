package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Participant {

    private Cards cards;

    public Participant(final Cards cards) {
        this.cards = cards;
    }

    public void receiveCard(Card card) {
        cards = cards.add(card);
    }

    public int calculateTotalScore() {
        return this.cards.calculateScoreForBlackjack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
