package blackjack.dto;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.BlackJackResult;
import blackjack.domain.result.PlayerResultMatcher;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class GamersResultDto {

    private final Map<BlackJackResult, Integer> dealerResult;
    private final Map<Player, BlackJackResult> playersResult;

    private GamersResultDto(Map<BlackJackResult, Integer> dealerResult, Map<Player, BlackJackResult> playersResult) {
        this.dealerResult = Collections.unmodifiableMap(dealerResult);
        this.playersResult = Collections.unmodifiableMap(playersResult);
    }

    public static GamersResultDto of(Dealer dealer, Players players) {
        EnumMap<BlackJackResult, Integer> dealerResult = new EnumMap<>(BlackJackResult.class);
        Map<Player, BlackJackResult> playersResult = new HashMap<>();

        for (Player player : players) {
            BlackJackResult result = PlayerResultMatcher.match(dealer, player);
            playersResult.put(player, result);
            dealerResult.computeIfPresent(result.reversed(), (key, value) -> ++value);
            dealerResult.putIfAbsent(result.reversed(), 1);
        }

        return new GamersResultDto(dealerResult, playersResult);
    }

    public Map<BlackJackResult, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<Player, BlackJackResult> getPlayersResult() {
        return playersResult;
    }
}