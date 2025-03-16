package blackjack.domain.money;

import java.util.List;

public record Money(int money) {
    
    public static int getTotalMoney(List<Money> amounts) {
        return amounts.stream()
                .mapToInt(amount -> amount.money)
                .sum();
    }
    
    public Money multiply(double factor) {
        return new Money((int) (this.money * factor));
    }
}
