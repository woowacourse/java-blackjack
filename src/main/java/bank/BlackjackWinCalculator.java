package bank;

public class BlackjackWinCalculator implements Calculator {
    @Override
    public Money calculate(Money money) {
        return Money.multiply(money, 1.5);
    }
}
