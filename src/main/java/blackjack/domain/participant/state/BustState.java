package blackjack.domain.participant.state;

import blackjack.domain.card.Card;

import java.util.ArrayList;

public class BustState extends HandState {
    public BustState(final HandState handState) {
        validateBustState(handState);
        this.cards = new ArrayList<>(handState.getCards());
    }

    private void validateBustState(final HandState handState) {
        if (handState.calculateScore() <= BLACKJACK_SCORE) {
            throw new IllegalStateException("버스트 상태의 카드패가 아닙니다.");
        }
    }

    @Override
    public HandState add(final Card cards) {
        throw new IllegalStateException("버스트는 더 이상 카드를 받을 수 없습니다.");
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }
}
