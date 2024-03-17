package domain.state.run;

import domain.card.Hands;
import domain.player.Result;
import domain.state.Started;
import domain.state.State;

public abstract class Running extends Started {
    public Running(final Hands hands) {
        super(hands);
    }

    @Override
    public final boolean isRunning() {
        return true;
    }

    @Override
    public Result compareWith(final State state) {
        throw new IllegalCallerException("상태가 실행 중이어서 비교가 불가능합니다");
    }
}
