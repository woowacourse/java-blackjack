package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ProfitResult {

    private final Map<Participant, Integer> result;

    public ProfitResult(final Map<Participant, Integer> result) {
        this.result = result;
    }

    public static ProfitResult from(final Dealer dealer,
                                    final Map<Player, ResultStatus> winningResult) {
        final Map<Participant, Integer> profits = initializeProfits(dealer, winningResult);
        for (Entry<Player, ResultStatus> entry : winningResult.entrySet()) {
            calculateEachProfit(dealer, entry, profits);
        }
        return new ProfitResult(profits);
    }

    private static Map<Participant, Integer> initializeProfits(final Dealer dealer,
                                                               final Map<Player, ResultStatus> winningResult) {
        return Stream.concat(Stream.of(dealer), winningResult.keySet().stream())
                .collect(Collectors.toMap(Function.identity(), key -> 0, (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    private static void calculateEachProfit(final Dealer dealer, final Entry<Player, ResultStatus> entry,
                                            final Map<Participant, Integer> profits) {
        final Player player = entry.getKey();
        final int bettingAmount = player.getBettingAmount();
        final int profit = (int) (entry.getValue().getProfitRate() * bettingAmount);
        profits.merge(dealer, -profit, Integer::sum);
        profits.merge(player, profit, Integer::sum);
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

    public Map<Participant, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
