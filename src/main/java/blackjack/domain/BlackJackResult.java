package blackjack.domain;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResult {

    private final Map<GameResult, Integer> dealerResult;
    private final Map<Player, GameResult> gamblerResult;

    private BlackJackResult(final Map<GameResult, Integer> dealerResult,
                            final Map<Player, GameResult> gamblerResult) {
        this.dealerResult = dealerResult;
        this.gamblerResult = gamblerResult;
    }

    public static BlackJackResult of(final Players players) {
        final Map<Player, GameResult> gamblerResult = new LinkedHashMap<>();
        final EnumMap<GameResult, Integer> dealerResult = players.getGamblers()
            .stream()
            .collect(Collectors.groupingBy(
                gambler -> getResultPlayer(players.getDealer(), gamblerResult, gambler),
                () -> new EnumMap<>(GameResult.class),
                Collectors.summingInt(count -> 1)
            ));
        return new BlackJackResult(dealerResult, gamblerResult);
    }

    private static GameResult getResultPlayer(final Player dealer,
                                              final Map<Player, GameResult> gamblerResult,
                                              final Player gambler) {
        final GameResult currentDealerResult = dealer.compare(gambler);
        gamblerResult.put(gambler, currentDealerResult.reverse());
        return dealer.compare(gambler);
    }

    public Map<GameResult, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<Player, GameResult> getGamblerResult() {
        return gamblerResult;
    }
}
