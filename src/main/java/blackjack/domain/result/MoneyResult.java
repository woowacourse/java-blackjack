package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Money;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Guests;
import java.util.LinkedHashMap;
import java.util.Map;

public class MoneyResult {

    private final Map<Guest, Money> moneys;

    public MoneyResult() {
        this.moneys = new LinkedHashMap<>();
    }

    public void calculateGuestMoney(final Dealer dealer, final Guests guests) {
        for (Guest guest : guests) {
            final Money profit = guest.getMoney();
            moneys.put(guest, profit.calculate(Result.of(dealer, guest)));
        }
    }

    public Money calculateDealerMoney() {
        final Long money = moneys.values()
                .stream()
                .map(Money::getValue)
                .reduce(0L, Long::sum);
        return Money.from(-money);
    }

    public Map<Guest, Money> getMoneys() {
        return new LinkedHashMap<>(moneys);
    }
}
