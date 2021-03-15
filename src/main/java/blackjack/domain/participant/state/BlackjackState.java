package blackjack.domain.participant.state;

import blackjack.domain.card.Card;

import java.util.ArrayList;

public class BlackjackState extends HandState {
    public BlackjackState(final HandState handState) {
        validateBlackjackState(handState);
        this.cards = new ArrayList<>(handState.getCards());
    }

    private void validateBlackjackState(final HandState handState) {
        if (handState.getCards().size() != INITIAL_CARD_GIVEN || handState.calculateScore() != BLACKJACK_SCORE) {
            throw new IllegalStateException("블랙잭 상태의 카드패가 아닙니다.");
        }
    }

    @Override
    public HandState add(final Card cards) {
        throw new IllegalStateException("블랙잭은 더 이상 카드를 받을 수 없습니다.");
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }
}
