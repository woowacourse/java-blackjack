package blackjack.model.state.finished;

import blackjack.model.card.Card;
import blackjack.model.state.Hand;
import blackjack.model.state.State;

public final class Blackjack extends FinishedState {

    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public State receiveCard(Card card) {
        throw new IllegalArgumentException("블랙잭이라 카드를 더 받을 수 없습니다.");
    }

    @Override
    public State stand() {
        throw new IllegalArgumentException("블랙잭이라 스탠드할 수 없습니다.");
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
