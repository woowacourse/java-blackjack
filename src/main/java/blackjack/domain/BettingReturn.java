package blackjack.domain;

import blackjack.constant.MatchResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BettingReturn {

    private final Map<String, Integer> playersReturn;
    private final int dealerReturn;

    public BettingReturn(Map<String, Integer> playersReturn, int dealerReturn) {
        this.playersReturn = playersReturn;
        this.dealerReturn = dealerReturn;
    }

    public static BettingReturn of(Dealer dealer, List<Player> players) {
        Map<String, Integer> playersReturn = calculatePlayersReturn(dealer, players);
        int dealerReturn = calculateDealerReturn(playersReturn);
        return new BettingReturn(playersReturn, dealerReturn);
    }

    public static BettingReturn of(Dealer dealer, Player... players) {
        return of(dealer, List.of(players));
    }

    private static Map<String, Integer> calculatePlayersReturn(Dealer dealer, List<Player> players) {
        Map<String, Integer> playersReturn = new LinkedHashMap<>();
        for (Player player : players) {
            int playerBettingAmount = player.bettingAmount();
            MatchResult matchResult = player.match(dealer);
            int playerReturn = matchResult.getReturn(playerBettingAmount);
            playersReturn.put(player.getName(), playerReturn);
        }
        return playersReturn;
    }

    private static int calculateDealerReturn(Map<String, Integer> playersEarnings) {

        return -1 * playersEarnings.values()
                .stream()
                .mapToInt(e -> e)
                .sum();
    }

    public int findPlayerReturn(String playerName) {
        return playersReturn.get(playerName);
    }

    public Map<String, Integer> getPlayersReturn() {
        return Collections.unmodifiableMap(playersReturn);
    }

    public int getDealerReturn() {
        return dealerReturn;
    }
}
