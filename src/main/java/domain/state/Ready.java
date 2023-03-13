package domain.state;

import domain.card.Hand;

public abstract class Ready extends State {
    Ready(Hand hand) {
        super(hand);
    }

    @Override
    public State stay() {
        throw new IllegalStateException("[ERROR] 게임 준비 단계에는 상태를 조작할 수 없습니다.");
    }

    @Override
    public int calculatePrize(int base) {
        throw new IllegalStateException("[ERROR] 아직 게임이 끝나지 않아 결과를 확인할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
