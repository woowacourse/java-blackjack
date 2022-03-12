package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResult {

    private final Map<GameResult, Integer> dealerResult;
    private final LinkedHashMap<Player, GameResult> gamblerResult;

    private BlackJackResult(final Map<GameResult, Integer> dealerResult,
                            final LinkedHashMap<Player, GameResult> gamblerResult) {
        this.dealerResult = dealerResult;
        this.gamblerResult = gamblerResult;
    }

    public static BlackJackResult of(Player dealer, List<Player> gamblers) {
        final LinkedHashMap<Player, GameResult> gamblerResult = new LinkedHashMap<>();
        final Map<GameResult, Integer> dealerResult = gamblers.stream()
            .collect(Collectors.groupingBy(
                gambler -> getResultPlayer(dealer, gamblerResult, gambler),
                Collectors.summingInt(count -> 1)
            ));
        return new BlackJackResult(dealerResult, gamblerResult);
    }

    private static GameResult getResultPlayer(final Player dealer,
                                              final LinkedHashMap<Player, GameResult> gamblerResult,
                                              final Player gambler) {
        final GameResult currentDealerResult = dealer.compare(gambler);
        gamblerResult.put(gambler, currentDealerResult.reverse());
        return dealer.compare(gambler);
    }

    public Map<GameResult, Integer> getDealerResult() {
        return dealerResult;
    }

    public LinkedHashMap<Player, GameResult> getGamblerResult() {
        return gamblerResult;
    }
}
