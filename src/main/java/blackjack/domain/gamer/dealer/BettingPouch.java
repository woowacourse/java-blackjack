package blackjack.domain.gamer.dealer;

import blackjack.domain.money.Money;
import blackjack.domain.money.RewardRate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BettingPouch {
    private final Map<String, Money> values;

    public BettingPouch() {
        this.values = new HashMap<>();
    }

    public void put(String name, Money money) {
        values.put(name, money);
    }

    public Map<String, Double> getGeneratePlayerRevenues(Map<String, RewardRate> rateByPlayerNames) {
        return values.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue()
                                .applyRate(rateByPlayerNames.getOrDefault(entry.getKey(), RewardRate.DRAW))
                ));
    }
}
