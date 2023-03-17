package domain.player;

import domain.card.Score;

import java.util.function.ToDoubleFunction;

public class Dealer extends Player {
    private static final Score DEALER_GIVE_CARD_STATE_MAX_SCORE = new Score(16);
    
    public Dealer() {
        super("딜러");
    }
    
    @Override
    public boolean isDealer() {
        return true;
    }
    
    @Override
    public double calculateProfit(double betAmount) {
        throw new IllegalStateException("딜러는 수익률 계산을 할 수 없습니다.");
    }
    
    @Override
    public boolean isFinished() {
        return getTotalScore().isOverThen(DEALER_GIVE_CARD_STATE_MAX_SCORE);
    }
    
    @Override
    public double supplyBetAmount(ToDoubleFunction<String> supplyBetAmount) {
        throw new UnsupportedOperationException("딜러는 배팅 금액을 입력받지 않습니다.");
    }
}
