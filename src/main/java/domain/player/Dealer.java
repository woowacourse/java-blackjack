package domain.player;

public class Dealer extends Player {
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
}
