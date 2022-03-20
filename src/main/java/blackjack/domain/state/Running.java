package blackjack.domain.state;

import blackjack.domain.card.Hand;

public abstract class Running extends Started {

    protected Running(Hand hand) {
        super(hand);
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final double earningRate(State state) {
        throw new UnsupportedOperationException("[ERROR] 수익율을 구하는걸 지원하지 않습니다.");
    }
}
