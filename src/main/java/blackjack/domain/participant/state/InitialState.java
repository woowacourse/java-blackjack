package blackjack.domain.participant.state;

import blackjack.domain.card.Card;

import java.util.ArrayList;

public class InitialState extends HandState {
    public InitialState() {
        this.cards = new ArrayList<>();
    }

    public InitialState(final HandState handState) {
        validateInitialState(handState);
        this.cards = new ArrayList<>(handState.getCards());
    }

    private void validateInitialState(final HandState handState) {
        if (handState.getCards().size() >= INITIAL_CARD_GIVEN) {
            throw new IllegalStateException("2장의 카드부터는 초기 상태의 카드패가 될 수 없습니다.");
        }
    }

    @Override
    public HandState add(final Card card) {
        cards.add(card);
        if (cards.size() < INITIAL_CARD_GIVEN) {
            return new InitialState(this);
        }
        if (cards.size() == INITIAL_CARD_GIVEN && calculateScore() == BLACKJACK_SCORE) {
            return new BlackjackState(this);
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
