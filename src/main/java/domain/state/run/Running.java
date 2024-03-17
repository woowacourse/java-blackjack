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
    public Result compareWith(final State state) {
        throw new UnsupportedOperationException("끝나지 않은 상태에서는 결과를 비교할 수 없습니다");
    }
}
