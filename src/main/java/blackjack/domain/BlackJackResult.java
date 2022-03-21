package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class BlackJackResult {

    private static final int DEALER_FLIP_UNIT = -1;

    private final Map<Player, Double> value;

    private BlackJackResult(final Map<Player, Double> value) {
        this.value = value;
    }

    public static BlackJackResult from(final Players players) {
        final Map<Player, Double> ProfitsPerPlayer = players.getGamblers()
            .stream()
            .collect(toMap(Function.identity(),
                gambler -> calculateProfit(players, gambler),
                (x, y) -> y,
                LinkedHashMap::new));

        return new BlackJackResult(ProfitsPerPlayer);
    }

    private static Double calculateProfit(final Players players, final Player gambler) {
        final GameResult gameResult = players.getDealer().compare(gambler);
        return gameResult.calculateProfit(gambler.getBetMoney());
    }

    public double calculateDealerProfit() {
        final double dealerTotalProfit = this.value.values()
            .stream()
            .mapToDouble(Double::valueOf)
            .sum();
        return dealerTotalProfit * DEALER_FLIP_UNIT;
    }

    public Map<Player, Double> getValue() {
        return value;
    }
}
