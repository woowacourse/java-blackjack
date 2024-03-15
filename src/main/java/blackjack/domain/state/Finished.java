package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

public abstract class Finished extends Started {

    Finished(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException("해당 플레이어의 턴이 이미 종료되었습니다.");
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException("해당 플레이어의 턴이 이미 종료되었습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
