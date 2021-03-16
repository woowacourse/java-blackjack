package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;
import blackjack.domain.user.Player;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Profit {
    private final List<Money> profit;

    public Profit() {
        this.profit = new ArrayList<>();
    }

    public void addEachPlayerProfit(Dealer dealer, List<Player> players) {
        this.profit.addAll(players.stream()
                .map(player -> player.state().profit(player.getMoney(), dealer))
                .collect(toList()));
    }

    public void addDealerProfit() {
        double dealerProfit = this.profit.stream()
                .mapToDouble(Money::getMoney)
                .reduce(0.0, Double::sum);
        this.profit.add(0, new Money(dealerProfit * -1.0));
    }

    public List<Money> getProfit() {
        return this.profit;
    }
}
