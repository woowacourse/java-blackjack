package blackjack.domain.result;

import blackjack.domain.participant.gamer.Dealer;
import blackjack.domain.participant.gamer.Gamer;
import blackjack.domain.participant.gamer.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ProfitResult {

    private final Map<Gamer, Integer> result;

    public ProfitResult(final Dealer dealer, final Map<Player, ResultStatus> winningResult) {
        this.result = calculateProfit(dealer, winningResult);
    }

    private Map<Gamer, Integer> calculateProfit(final Dealer dealer, final Map<Player, ResultStatus> winningResult) {
        final Map<Gamer, Integer> profits = initializeProfits(dealer, winningResult);
        for (Entry<Player, ResultStatus> entry : winningResult.entrySet()) {
            calculateEachProfit(dealer, entry, profits);
        }
        return profits;
    }

    private Map<Gamer, Integer> initializeProfits(final Dealer dealer, final Map<Player, ResultStatus> winningResult) {
        return Stream.concat(Stream.of(dealer), winningResult.keySet().stream())
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
        if (!(o instanceof final ProfitResult that)) {
            return false;
        }
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }

    public Map<Gamer, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
