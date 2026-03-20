package domain.state;

import domain.Result;
import domain.card.Hand;

public abstract class Running extends Started {
    public Running(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Result judge(State state) {
        throw new UnsupportedOperationException("[ERROR] 진행 중에는 결과를 판단할 수 없습니다.");
    }
}
