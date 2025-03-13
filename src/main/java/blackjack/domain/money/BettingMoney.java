package blackjack.domain.money;

public class BettingMoney {
    
    private final Money money;
    
    public BettingMoney(int money) {
        this.money = new Money(money);
    }
    
    public Money multiply(double factor) {
        return money.multiply(factor);
    }
}
