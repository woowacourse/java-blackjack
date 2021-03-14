package blackjack.domain.participant.state;

import blackjack.domain.card.Card;

import java.util.ArrayList;

public class HitState extends HandState {
    public HitState(final HandState handState) {
        validateHitState(handState);
        this.cards = new ArrayList<>(handState.getCards());
    }

    private void validateHitState(final HandState handState) {
        if (handState.getCards().size() < INITIAL_CARD_GIVEN || handState.calculateScore() >= BLACKJACK_SCORE) {
            throw new IllegalStateException("히트 상태의 카드패가 아닙니다.");
        }
    }

    @Override
    public HandState add(final Card card) {
        cards.add(card);
        if (calculateScore() > BLACKJACK_SCORE) {
            return new BustState(this);
        }
        return new HitState(this);
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }
}
