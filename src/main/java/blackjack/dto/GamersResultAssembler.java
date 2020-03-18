package blackjack.dto;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.BlackJackResult;
import blackjack.domain.result.PlayerResultMatcher;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GamersResultAssembler {

    public static GamersResultDto of(Dealer dealer, Players players) {
        EnumMap<BlackJackResult, Integer> dealerResult = new EnumMap<>(BlackJackResult.class);
        Map<Player, BlackJackResult> playersResult = new LinkedHashMap<>();

        for (Player player : players) {
            BlackJackResult result = PlayerResultMatcher.match(dealer, player);
            playersResult.put(player, result);
            dealerResult.computeIfPresent(result.reversed(), (key, value) -> ++value);
            dealerResult.putIfAbsent(result.reversed(), 1);
        }

        return new GamersResultDto(dealerResult, playersResult);
    }
}
