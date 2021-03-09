package blackjack.domain.state;

import blackjack.domain.card.Card;

public abstract class Finished implements State {
    private static final String CANNOT_DRAW_CARD_ERROR_MESSAGE = "카드를 뽑을 수 없는 상태입니다.";

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException(CANNOT_DRAW_CARD_ERROR_MESSAGE);
    }
}
