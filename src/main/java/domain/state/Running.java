package domain.state;

import domain.card.Hand;

public abstract class Running extends State {
    Running(Hand hand) {
        super(hand);
    }

    @Override
    public State stay() {
        return new Stay(getHand());
    }

    @Override
    public double getProfitRatio() {
        throw new IllegalStateException("[ERROR] 아직 게임이 끝나지 않아 결과를 확인할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
