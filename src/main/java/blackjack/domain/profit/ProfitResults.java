package blackjack.domain.profit;

import blackjack.domain.money.Money;
import blackjack.domain.participant.Player;

import java.util.Map;

public record ProfitResults(Map<Player, Money> profitResults) {

    public Money dealerProfit() {
        return profitResults.values().stream()
                .map(Money::negate)
                .reduce(new Money(0), Money::add);
    }

    public Money profitOf(Player player) {
        return profitResults.get(player);
    }
}
