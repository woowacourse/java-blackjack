package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Score;
import java.util.List;

public class Participant {

    private Hand hand;

    public Participant(final Hand hand) {
        this.hand = hand;
    }

    public void receiveCard(Card card) {
        hand = hand.add(card);
    }

    public Score calculateTotalScore() {
        return this.hand.calculateScoreForBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public List<Card> getHand() {
        return hand.getHand();
    }
}
