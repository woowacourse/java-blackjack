package hand;

import static result.GameStatus.*;

import card.Card;
import java.util.List;
import result.GameStatus;

public class BlackjackHand extends Hand {
    public BlackjackHand(List<Card> hand) {
        super(hand);
    }

    @Override
    public GameStatus calculateResultAgainst(Hand other) {
        if (isBlackjack() && other.isBlackjack()) {
            return DRAW;
        }
        return BLACKJACK_WIN;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
