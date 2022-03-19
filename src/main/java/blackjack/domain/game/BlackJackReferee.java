package blackjack.domain.game;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BettingResult;

public class BlackJackReferee {

    private static final int DEFAULT_EARNING = 0;

    public static BettingResult create(Dealer dealer, List<Player> players) {
        int dealerEarning = DEFAULT_EARNING;
        Map<String, Integer> playerEarnings = new LinkedHashMap<>();

        for (Player player : players) {
            int playerEarning = player.match(dealer);
            playerEarnings.put(player.getName(), playerEarning);
            dealerEarning += calculateReverseEarning(playerEarning);
        }
        return new BettingResult(dealerEarning, playerEarnings);
    }

    private static int calculateReverseEarning(int earning) {
        return earning * -1;
    }
}
