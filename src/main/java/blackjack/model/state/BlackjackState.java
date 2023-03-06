package blackjack.model.state;

import blackjack.model.card.HandCard;
import blackjack.model.card.CardDeck;

public class BlackjackState extends State {

    public static final int BLACKJACK_SCORE = 21;

    public BlackjackState(HandCard handCard) {
        super(handCard);
        validateIsHandCard21(handCard);
    }

    @Override
    public State draw(CardDeck cardDeck) {
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

    @Override
    public boolean isStand() {
        return false;
    }

    private void validateIsHandCard21(HandCard handCard) {
        if (!handCard.isBigScoreEqual(BLACKJACK_SCORE)) {
            throw new IllegalArgumentException("점수가 21이 아닌 카드들입니다. 블랙잭이 될 수 없습니다.");
        }
    }
}
