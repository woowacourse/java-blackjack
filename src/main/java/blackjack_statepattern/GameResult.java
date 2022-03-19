package blackjack_statepattern;

import blackjack_statepattern.participant.Dealer;
import blackjack_statepattern.participant.Participant;
import blackjack_statepattern.participant.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private static final int REVERSE = -1;
    private final Map<Participant, Double> gameResult;

    private GameResult(Map<Participant, Double> gameResult) {
        this.gameResult = gameResult;
    }

    public static GameResult of(Dealer dealer, List<Player> players) {
        Map<Participant, Double> participantsResult = new LinkedHashMap<>();
        for (Player player : players) {
            double profit = player.profit(dealer.getCards());
            participantsResult.merge(dealer, reverseProfit(profit), Double::sum);
            participantsResult.put(player, profit);
        }
        return new GameResult(participantsResult);
    }

    private static double reverseProfit(double profit) {
        return REVERSE * profit;
    }

    public Map<Participant, Double> getGameResult() {
        return gameResult;
    }
}
