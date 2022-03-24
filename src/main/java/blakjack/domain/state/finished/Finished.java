package blakjack.domain.state.finished;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;
import blakjack.domain.state.State;

public abstract class Finished extends State {
    private static final String CANNOT_DRAW_MESSAGE = "카드를 더이상 받을수 없습니다.";
    private static final String CANNOT_STAY_MESSAGE = "스테이 할 수 없습니다.";
    private static final String CANNOT_GET_PROFIT_MESSAGE = "게임결과 상태가 아니기때문에 수익을 구할수 없습니다.";

    Finished(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException(CANNOT_DRAW_MESSAGE);
    }

    @Override
    public final State stay() {
        throw new IllegalStateException(CANNOT_STAY_MESSAGE);
    }

    @Override
    public int getProfit() {
        throw new IllegalStateException(CANNOT_GET_PROFIT_MESSAGE);
    }
}
