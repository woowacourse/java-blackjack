package domain.state;

import domain.card.Hand;

public abstract class Running extends State {
    Running(Hand hand) {
        super(hand);
    }
    
    @Override
    public double calculateProfit(int betAmount) {
        throw new IllegalStateException("아직 배팅 금액을 계산할 수 없는 상태입니다.");
    }
}
