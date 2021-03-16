package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class MatchResult {
    private final Map<Player, MatchResultType> matchResult;

    public MatchResult(Dealer dealer, Players players) {
        matchResult = calculateMatchResult(dealer, players);
    }

    private Map<Player, MatchResultType> calculateMatchResult(Dealer dealer, Players players) {
        Map<Player, MatchResultType> matchResult = new LinkedHashMap<>();

        for (Player player : players.getPlayers()) {
            matchResult.put(player, dealer.compareScore(player));
        }
        return Collections.unmodifiableMap(matchResult);
    }

    public double getDealerProfitResult() {
        return getPlayersProfitResult().values().stream()
                .mapToDouble(i -> -i)
                .sum();
    }

    public Map<Player, Double> getPlayersProfitResult() {
        Map<Player, Double> profitResult = new LinkedHashMap<>();
        this.matchResult.forEach(
                ((player, matchResultType) -> profitResult.put(player, matchResultType.calculateProfit(player.getBettingMoney())))
        );
        return Collections.unmodifiableMap(profitResult);
    }
}
