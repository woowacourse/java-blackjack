package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ProfitResult {

    private final Map<Participant, BigDecimal> result;

    public ProfitResult(final Map<Participant, BigDecimal> result) {
        this.result = result;
    }

    public static ProfitResult from(final Dealer dealer,
                                    final Map<Player, ResultStatus> winningResult) {
        final Map<Participant, BigDecimal> profits = initializeProfit(dealer, winningResult);
        for (Entry<Player, ResultStatus> entry : winningResult.entrySet()) {
            calculateEachProfit(dealer, entry, profits);
        }
        return new ProfitResult(profits);
    }

    private static Map<Participant, BigDecimal> initializeProfit(final Dealer dealer,
                                                                 final Map<Player, ResultStatus> winningResult) {
        final Stream<Dealer> dealerStream = Stream.of(dealer);
        final Stream<Player> playersStream = winningResult.keySet().stream();
        return Stream.concat(dealerStream, playersStream)
                .collect(Collectors.toMap(
                        participant -> participant, key -> BigDecimal.ZERO,
                        (existing, replacement) -> existing, LinkedHashMap::new)
                );
    }

    private static void calculateEachProfit(final Dealer dealer, final Entry<Player, ResultStatus> entry,
                                            final Map<Participant, BigDecimal> profits) {
        final Player player = entry.getKey();
        final BigDecimal bettingAmount = player.getBettingAmount();
        final BigDecimal profit = entry.getValue().calculateProfit(bettingAmount);
        profits.merge(dealer, profit.multiply(BigDecimal.valueOf(-1)), BigDecimal::add);
        profits.merge(player, profit, BigDecimal::add);
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

    public Map<Participant, BigDecimal> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
