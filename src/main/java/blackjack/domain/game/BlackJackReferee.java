package blackjack.domain.game;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.gamer.Bet;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackResult;

public class BlackJackReferee {

    private static final int DEFAULT_EARNING = 0;

    private final int dealerEarning;
    private final Map<String, Integer> playerEarnings;

    private BlackJackReferee(int dealerEarning, Map<String, Integer> playerEarnings) {
        this.dealerEarning = dealerEarning;
        this.playerEarnings = playerEarnings;
    }

    public static BlackJackReferee create(Dealer dealer, List<Player> players) {
        int dealerEarning = DEFAULT_EARNING;
        Map<String, Integer> playerEarnings = new LinkedHashMap<>();

        for (Player player : players) {
            BlackJackResult result = player.match(dealer);
            int playerEarning = player.calculateEarning(result.getProfit());

            playerEarnings.put(player.getName(), playerEarning);
            dealerEarning += Bet.calculateMinusAmount(playerEarning);
        }
        return new BlackJackReferee(dealerEarning, playerEarnings);
    }

    public int getDealerEarning() {
        return dealerEarning;
    }

    public Map<String, Integer> getPlayerEarnings() {
        return new LinkedHashMap<>(playerEarnings);
    }
}
