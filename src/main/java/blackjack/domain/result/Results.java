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

    private final Map<Player, ResultType> winOrLoseMap;
    private final Map<Player, Money> profitMap;

    public Results(Map<Player, ResultType> winOrLoseMap, Map<Player, Money> profitMap) {
        this.winOrLoseMap = winOrLoseMap;
        this.profitMap = profitMap;
    }

    public static Results of(Map<Player, ResultType> winOrLoseResults) {
        Map<Player, Money> profitMap = winOrLoseResults.keySet().stream().parallel()
                .collect(toMap(
                        player -> player,
                        player -> ProfitTable.translateBettingMoney(winOrLoseResults.get(player), player.getBettingMoney())));
        return new Results(winOrLoseResults, profitMap);
    }

    public ResultType getResultOf(Player player) {
        return winOrLoseMap.get(player);
    }

    public Money getEarningMoneyOf(Player player) {
        return profitMap.get(player);
    }

    public DealerResult generateDealerResult() {
        HashMap<ResultType, Integer> dealerWinOrLoseMap = new HashMap<>();
        Arrays.stream(ResultType.values())
                .forEach(resultType -> {
                    dealerWinOrLoseMap.put(resultType, INITIALIZE_VALUE);
                    dealerWinOrLoseMap.put(
                            resultType.reverse(),
                            dealerWinOrLoseMap.get(resultType) + this.count(resultType));
                });
        return new DealerResult(dealerWinOrLoseMap, this.getTotalProfit() * TO_NEGATIVE_VALUE);
    }

    private long getTotalProfit() {
        return profitMap.values().stream()
                .mapToLong(Money::toLong)
                .sum();
    }

    private int count(ResultType resultType) {
        return (int) this.winOrLoseMap.values().stream().parallel()
                .filter(resultType::equals)
                .count();
    }

    public Set<Player> playerSet() {
        return winOrLoseMap.keySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Results results1 = (Results) o;
        return Objects.equals(winOrLoseMap, results1.winOrLoseMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winOrLoseMap, profitMap);
    }
}
