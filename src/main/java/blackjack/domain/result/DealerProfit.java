package blackjack.domain.result;

import blackjack.domain.player.Money;

public class DealerProfit {

    private final Money profit;

    public DealerProfit(final GuestProfit guestProfit) {
        this.profit = calculate(guestProfit);
    }

    private Money calculate(final GuestProfit guestProfit) {
        final Long profit = guestProfit.getValues()
                .stream()
                .map(Money::getValue)
                .reduce(0L, Long::sum);
        return Money.valueOf(-profit);
    }

    public Money getProfit() {
        return profit;
    }
}
