package blackjack.domain.result;

import blackjack.domain.money.Money;

public class DealerProfit {

    private final Money profit;

    public DealerProfit(final GuestProfits guestProfits) {
        this.profit = calculate(guestProfits);
    }

    private Money calculate(final GuestProfits guestProfits) {
        final Long profit = guestProfits.getValues()
                .stream()
                .map(Money::getValue)
                .reduce(0L, Long::sum);
        return Money.valueOf(-profit);
    }

    public Money getProfit() {
        return profit;
    }
}
