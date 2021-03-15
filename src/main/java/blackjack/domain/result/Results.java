package blackjack.domain.result;

import blackjack.domain.Money;
import blackjack.domain.ProfitTable;
import blackjack.domain.ResultType;
import blackjack.domain.user.Player;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Objects;

import static java.util.stream.Collectors.*;

public class Results {
    private static final long TO_NEGATIVE_VALUE = -1;
    private static final int INITIALIZE_VALUE = 0;

    private final Map<Player, ResultType> results;
    private final Map<Player, Money> profits;

    public Results(Map<Player, ResultType> results, Map<Player, Money> profits) {
        this.results = results;
        this.profits = profits;
    }

    public static Results of(Map<Player, ResultType> results) {
        Map<Player, Money> profitMap = results.keySet().stream().parallel()
                .collect(toMap(
                        player -> player,
                        player -> ProfitTable.translateBettingMoney(results.get(player), player.getBettingMoney())));
        return new Results(results, profitMap);
    }

    public ResultType getResultOf(Player player) {
        return results.get(player);
    }

    public Money getEarningMoneyOf(Player player) {
        return profits.get(player);
    }

    public DealerResult generateDealerResult() {
        HashMap<ResultType, Integer> dealerWinOrLoseMap = new HashMap<>();
        Arrays.stream(ResultType.values())
                .forEach(resultType -> dealerWinOrLoseMap.put(resultType, INITIALIZE_VALUE));
        Arrays.stream(ResultType.values())
                .forEach(resultType -> {
                    dealerWinOrLoseMap.put(
                            resultType.reverse(),
                            dealerWinOrLoseMap.get(resultType.reverse()) + this.count(resultType));
                });
        return new DealerResult(dealerWinOrLoseMap, this.getTotalProfit() * TO_NEGATIVE_VALUE);
    }

    private long getTotalProfit() {
        return profits.values().stream()
                .mapToLong(Money::toLong)
                .sum();
    }

    private int count(ResultType resultType) {
        return (int) this.results.values().stream().parallel()
                .filter(resultType::equals)
                .count();
    }

    public Set<Player> playerSet() {
        return results.keySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Results results1 = (Results) o;
        return Objects.equals(results, results1.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, profits);
    }
}
