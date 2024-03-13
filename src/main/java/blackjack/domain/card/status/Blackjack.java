package blackjack.domain.card.status;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.Objects;

public class Blackjack implements HandStatus {

    private final Hand hand;

    public Blackjack(Hand hand) {
        validateBlackjack(hand);
        this.hand = hand;
    }

    private void validateBlackjack(Hand hand) {
        if (!hand.isBlackjack()) {
            throw new IllegalArgumentException("해당 카드는 블랙잭이 아닙니다.");
        }
    }

    @Override
    public HandStatus add(Card card) {
        return null;
    }

    @Override
    public SingleMatchResult matchAtPlayer(HandStatus dealerHand) {
        return null;
    }

    @Override
    public SingleMatchResult matchAtDealer(HandStatus playerHand) {
        return null;
    }
}
