package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Gamer;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DealerWinningResult {

    private final Map<Player, ResultStatus> result;

    public DealerWinningResult(final Map<Player, ResultStatus> result) {
        this.result = new LinkedHashMap<>(result);
    }

    public Map<Gamer, Integer> calculateProfit(final Dealer dealer) {
        final Map<Gamer, Integer> profits = initializeProfits(dealer);
        for (Entry<Player, ResultStatus> entry : result.entrySet()) {
            calculateEachProfit(dealer, entry, profits);
        }
        return profits;
    }

    private Map<Gamer, Integer> initializeProfits(final Dealer dealer) {
        return Stream.concat(Stream.of(dealer), result.keySet().stream())
                .collect(Collectors.toMap(Function.identity(), key -> 0, (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    private void calculateEachProfit(final Dealer dealer, final Entry<Player, ResultStatus> entry,
                                     final Map<Gamer, Integer> profits) {
        Player player = entry.getKey();
        int bettingAmount = player.getBettingAmount();
        final int averageProfit = (int) (1.0 * bettingAmount);
        // 딜러가 이긴 경우
        if (entry.getValue() == ResultStatus.WIN) {
            profits.merge(dealer, +averageProfit, Integer::sum);
            profits.merge(player, -averageProfit, Integer::sum);
            return;
        }
        // 플레이어가 이긴 경우
        if (entry.getValue() == ResultStatus.LOSE) {
            profits.merge(dealer, -averageProfit, Integer::sum);
            profits.merge(player, averageProfit, Integer::sum);
            return;
        }
        // 블랙잭의 경우
        if (entry.getValue() == ResultStatus.BLACKJACK) {
            final int blackjackProfit = (int) (1.5 * bettingAmount);
            profits.merge(dealer, -blackjackProfit, Integer::sum);
            profits.merge(player, blackjackProfit, Integer::sum);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final DealerWinningResult that)) {
            return false;
        }
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }
}
