package blackjack.model.state;

import blackjack.model.card.HandCard;
import blackjack.model.card.CardDeck;

public class BlackjackState implements ParticipantState {

    public BlackjackState() {
    }

    @Override
    public ParticipantState draw(CardDeck cardDeck, HandCard handCard) {
        throw new IllegalStateException("블랙잭 상태에서는 카드를 더 뽑을 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
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
