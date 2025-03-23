package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ProfitResult {

    private final Map<Participant, BigDecimal> result;

    public ProfitResult(final Map<Participant, BigDecimal> result) {
        this.result = result;
    }

    public static ProfitResult from(final Dealer dealer, final Players players) {
        final Map<Participant, BigDecimal> profits = initializeProfit(dealer, players);
        for (Player player : players.getPlayers()) {
            calculateEachProfit(dealer, player, profits);
        }
        return new ProfitResult(profits);
    }

    private static Map<Participant, BigDecimal> initializeProfit(final Dealer dealer,
                                                                 final Players players) {
        final Stream<Dealer> dealerStream = Stream.of(dealer);
        final Stream<Player> playerStream = players.getPlayers().stream();
        return Stream.concat(dealerStream, playerStream)
                .collect(Collectors.toMap(
                        participant -> participant, key -> BigDecimal.ZERO,
                        (existing, replacement) -> existing, LinkedHashMap::new)
                );
    }

    private static void calculateEachProfit(final Dealer dealer, final Player player,
                                            final Map<Participant, BigDecimal> profits) {
        final BigDecimal profit = player.calculateProfit(dealer.getState());
        profits.merge(dealer, profit.multiply(BigDecimal.valueOf(-1)), BigDecimal::add);
        profits.merge(player, profit, BigDecimal::add);
    }

    public Map<Participant, BigDecimal> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
