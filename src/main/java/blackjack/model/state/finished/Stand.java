package blackjack.model.state.finished;

import blackjack.model.Money;
import blackjack.model.deck.Card;
import blackjack.model.participant.Hand;
import blackjack.model.state.State;

public class Stand implements State {
    private final Hand hand;

    public Stand(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException("Stand 상태이므로 더 이상 카드를 받을 수 없습니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("이미 Stand 입니다.");
    }

    @Override
    public Hand hand() {
        return hand;
    }

    @Override
    public int calculateScore() {
        return hand.calculateScore();
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
