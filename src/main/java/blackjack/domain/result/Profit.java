package blackjack.domain.result;

import blackjack.domain.user.Money;
import blackjack.domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.*;

public class Profit {
    private final List<Money> profit;

    public Profit(List<Player> players) {
        this.profit = new ArrayList<>();
        addDealerProfit(players);
        addEachPlayerProfit(players);
    }

    private void addDealerProfit(List<Player> players) {
        double dealerProfit = players.stream()
                .map(Player::getMoney)
                .reduce(0.0, Double::sum);
        profit.add(new Money(dealerProfit * -1.0));
    }

    private void addEachPlayerProfit(List<Player> players) {
        profit.addAll(players.stream()
                .map(Player::getMoney)
                .map(Money::new)
                .collect(toList()));
    }

    public List<Money> getProfit() {
        return Collections.unmodifiableList(this.profit);
    }
}
