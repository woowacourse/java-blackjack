package blackjack.blackjack.result;

import blackjack.blackjack.participant.Dealer;
import blackjack.blackjack.participant.Participant;
import blackjack.blackjack.participant.Player;
import blackjack.blackjack.participant.Players;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ProfitResult {

    private final Map<Participant, BigDecimal> profitByParticipant;

    public ProfitResult(final Map<Participant, BigDecimal> profitByParticipant) {
        this.profitByParticipant = profitByParticipant;
    }

    public static ProfitResult from(final Dealer dealer, final Players players) {
        final Map<Participant, BigDecimal> profit = initializeProfit(dealer, players);
        for (Player player : players.getPlayers()) {
            calculateEachProfit(dealer, player, profit);
        }
        return new ProfitResult(profit);
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
        final BigDecimal profit = player.calculateProfit(dealer);
        profits.merge(dealer, profit.negate(), BigDecimal::add);
        profits.merge(player, profit, BigDecimal::add);
    }

    public Map<Participant, BigDecimal> getProfitByParticipant() {
        return Collections.unmodifiableMap(profitByParticipant);
    }
}
