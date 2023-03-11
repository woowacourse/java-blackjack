package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import java.util.List;

public class Participant {

    private Cards cards;

    public Participant(final Cards cards) {
        this.cards = cards;
    }

    public void receiveCard(Card card) {
        cards = cards.add(card);
    }

    public Score calculateTotalScore() {
        return this.cards.calculateScoreForBlackjack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
