package blackjack.domain;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BlackJackResult {

    private static final int DEALER_FLIP_UNIT = -1;

    private final Map<Player, Long> value;

    public BlackJackResult(final Map<Player, Long> value) {
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

    private static Long calculateProfit(final Players players, final Player gambler) {
        final GameResult gameResult = players.getDealer().compare(gambler);
        return gameResult.calculateProfit(gambler.getBetMoney());
    }

    public long calculateDealerProfit() {
        return this.value.values()
            .stream()
            .mapToLong(Long::valueOf)
            .sum() * DEALER_FLIP_UNIT;
    }

    public Map<Player, Long> getValue() {
        return value;
    }
}
