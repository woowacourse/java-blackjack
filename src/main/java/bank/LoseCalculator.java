package bank;

public class LoseCalculator implements Calculator {
    @Override
    public Money calculate(Money money) {
        return Money.multiply(money, -1);
    }
}
