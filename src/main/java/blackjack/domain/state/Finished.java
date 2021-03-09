package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finished extends Started {
    private static final String CANNOT_DRAW_CARD_ERROR_MESSAGE = "카드를 뽑을 수 없는 상태입니다.";

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException(CANNOT_DRAW_CARD_ERROR_MESSAGE);
    }
}
