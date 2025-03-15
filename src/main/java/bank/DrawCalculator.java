package bank;

public class DrawCalculator implements Calculator {
    @Override
    public Money calculate(Money money) {
        return Money.multiply(money, 0);
    }
}
