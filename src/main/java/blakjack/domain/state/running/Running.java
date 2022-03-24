package blakjack.domain.state.running;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.state.State;

public abstract class Running extends State {
    private static final String CANNOT_GET_PROFIT_MESSAGE = "게임이 끝난뒤에 수익을 구할수 있습니다.";

    Running(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    public final State compare(State dealerState) {
        throw new IllegalStateException();
    }

    @Override
    public int getProfit() {
        throw new IllegalStateException(CANNOT_GET_PROFIT_MESSAGE);
    }
}
