package bank;

public class WinCalculator implements Calculator{
    @Override
    public Money calculate(Money money) {
        return Money.multiply(money, 1);
    }
}
