package blackjack.model.state;

import blackjack.model.card.HandCard;
import blackjack.model.card.CardDeck;

public class BustState extends ParticipantState {

    public static final int BUST_THROTTLE = 21;

    public BustState(HandCard handCard) {
        super(handCard);
        validateIsHandCardOver21(handCard);
    }

    @Override
    public ParticipantState draw(CardDeck cardDeck) {
        throw new IllegalStateException("버스트 상태에서는 카드를 더 뽑을 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    private void validateIsHandCardOver21(HandCard handCard) {
        if (!(handCard.isBigScoreOver(BUST_THROTTLE) && handCard.isSmallScoreOver(21))) {
            throw new IllegalArgumentException("카드 합이 21이하입니다. 버스트 상태가 될 수 없습니다.");
        }
    }
}
