package blackjack.model.state.finished;

import blackjack.model.card.Card;
import blackjack.model.state.Hand;
import blackjack.model.state.State;

public final class Stand extends FinishedState {

    public Stand(Hand hand) {
        super(hand);
    }

    @Override
    public State receiveCard(Card card) {
        throw new IllegalArgumentException("이미 스탠드하여 카드를 받을 수 없습니다.");
    }

    @Override
    public State stand() {
        throw new IllegalArgumentException("이미 스탠드했습니다.");
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
