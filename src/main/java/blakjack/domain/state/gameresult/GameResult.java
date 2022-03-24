package blakjack.domain.state.gameresult;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;
import blakjack.domain.state.State;

public abstract class GameResult extends State {
    private static final String CANNOT_COMPARE_MESSAGE = "게임이 끝나 더이상 비교할 수 없습니다.";
    private static final String CANNOT_DRAW_MESSAGE = "게임이 끝나 더이상 카드를 받을수 없습니다.";
    private static final String CANNOT_STAY_MESSAGE = "게임이 이미 끝났기 때문에 스테이할 수 없습니다.";

    GameResult(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    public final State compare(State dealerState) {
        throw new IllegalStateException(CANNOT_COMPARE_MESSAGE);
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException(CANNOT_DRAW_MESSAGE);
    }

    @Override
    public final State stay() {
        throw new IllegalStateException(CANNOT_STAY_MESSAGE);
    }
}
