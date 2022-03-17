package blackjack.domain;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BlackJackResult {

    private static final int DEALER_FLIP_UNIT = -1;

    private final Map<Player, Double> value;

    public BlackJackResult(final Map<Player, Double> value) {
        this.value = value;
    }

    public static BlackJackResult from(final Players players) {
        return new BlackJackResult(players.getGamblers()
            .stream()
            .collect(Collectors.toMap(
                Function.identity(),
                gambler -> calculateProfit(players, gambler),
                (x, y) -> y,
                LinkedHashMap::new)
            ));
    }

    private static Double calculateProfit(final Players players, final Player gambler) {
        final GameResult gameResult = players.getDealer().compare(gambler);
        return gameResult.calculateProfit(gambler.getBetMoney());
    }

    public double calculateDealerProfit() {
        return this.value.values()
            .stream()
            .mapToDouble(Double::valueOf)
            .sum() * DEALER_FLIP_UNIT;
    }

    public Map<Player, Double> getValue() {
        return value;
    }
}
