package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Guests;
import blackjack.domain.player.Money;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class GuestProfit {

    private final Map<Guest, Money> profits;

    public GuestProfit(final Dealer dealer, final Guests guests) {
        this.profits = calculate(dealer, guests);
    }

    private Map<Guest, Money> calculate(final Dealer dealer, final Guests guests) {
        final Map<Guest, Money> profits = new LinkedHashMap<>();
        for (Guest guest : guests) {
            final Money money = guest.getMoney();
            profits.put(guest, money.calculate(Result.of(dealer, guest)));
        }
        return profits;
    }

    Collection<Money> getValues() {
        return profits.values();
    }

    public Map<Guest, Money> getProfits() {
        return profits;
    }
}
