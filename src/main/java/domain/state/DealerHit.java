package domain.state;

import domain.card.Card;
import domain.member.Hand;

public class DealerHit extends Running {
    private static final int DEALER_DRAW_CONDITION = 16;

    public DealerHit(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        Hand newHand = hand.appendCard(card);
        int score = newHand.calculateTotalValue();
        int newHandSize = newHand.size();

        if (score >= BUST_CONDITION) {
            return new Bust(newHand);
        }
        if (newHandSize < INITIAL_CARDS_COUNT) {
            return new DealerHit(newHand);
        }
        if (newHandSize == INITIAL_CARDS_COUNT) {
            return judgeInitialState(newHand, score);
        }
        return new Stay(newHand);
    }

    private State judgeInitialState(Hand newHand, int score) {
        if (score == BLACKJACK_CONDITION) {
            return new Blackjack(newHand);
        }
        if (score > DEALER_DRAW_CONDITION) {
            return new Stay(newHand);
        }
        return new DealerHit(newHand);
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException("딜러는 스스로 멈출 수 없습니다.");
    }
}
