package domain.state;

import domain.BlackjackRule;
import domain.Hand;
import domain.card.Card;

public final class Hit extends Started {
    public static final int BLACKJACK_HAND_SIZE = 2;

    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public PlayerState draw(Card card) {
        hand.addCard(card);

        if (hand.getSum() > BlackjackRule.BLACKJACK_SCORE) {
            return new Bust(hand);
        }
        if (hand.getSize() == BLACKJACK_HAND_SIZE && hand.getSum() == BlackjackRule.BLACKJACK_SCORE) {
            return new BlackJack(hand);
        }
        if (hand.getSum() == BlackjackRule.BLACKJACK_SCORE) {
            return new Stay(hand);
        }
        return this;
    }

    @Override
    public PlayerState onStay() {
        return new Stay(hand);
    }
}
