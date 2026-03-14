package domain.state;

import domain.member.Hand;

public class Stay extends Finished {

    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate() {
        // 기본 수익률만 작성
        // 이후 딜러와 비교하는 로직을 추가할 예정
        return 1.0;
    }
}
