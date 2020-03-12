package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.*;

public class PlayerResultMatcher {

    public static GamersResult report(Dealer dealer, List<Player> players) {
        EnumMap<BlackJackResult, Integer> dealerResult = new EnumMap<>(BlackJackResult.class);
        Arrays.stream(BlackJackResult.values())
                .forEach(blackJackResult -> dealerResult.put(blackJackResult, 0));

        Map<Player, BlackJackResult> playersResult = new HashMap<>();

        for (Player player : players) {
            BlackJackResult result = match(dealer, player);
            playersResult.put(player, result);
            dealerResult.computeIfPresent(result.reversed(), (key, value) -> ++value);
        }

        return new GamersResult(dealerResult, playersResult);
    }

    public static BlackJackResult match(Dealer dealer, Player player) {
        if (!player.isBusted()) {
            if (!dealer.isBusted()) {
                return findWinner(dealer, player);
            }
            return BlackJackResult.WIN;
        }
        return BlackJackResult.LOSE;
    }

    private static BlackJackResult findWinner(Dealer dealer, Player player) {
        if (dealer.calculateSum() < player.calculateSum())
            return BlackJackResult.WIN;
        if (dealer.calculateSum() > player.calculateSum())
            return BlackJackResult.LOSE;
        return BlackJackResult.DRAW;
    }

}
