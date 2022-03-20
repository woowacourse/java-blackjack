package blackjack;

import blackjack.constant.MatchResult;
import blackjack.domain.BettingMoney;
import blackjack.domain.ScoreBoard;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RevenueResult {

    private final Map<String, Integer> playersEarnings ;
    private final int dealerEarnings;

    public RevenueResult(Map<String, Integer> playersEarnings, int dealerEarnings) {
        this.playersEarnings = playersEarnings;
        this.dealerEarnings = dealerEarnings;
    }

    public static RevenueResult of(ScoreBoard scoreBoard, List<BettingMoney> bettingMonies) {
        Map<String, Integer> playersEarnings = calculatePlayerEarnings(scoreBoard, bettingMonies);
        int dealerEarnings = calculateDealerEarnings(playersEarnings);
        return new RevenueResult(playersEarnings, dealerEarnings);
    }

    private static Map<String, Integer> calculatePlayerEarnings(ScoreBoard scoreBoard, List<BettingMoney> bettingMonies) {
        Map<String, Integer> playersEarnings = new LinkedHashMap<>();
        for (BettingMoney bettingMoney : bettingMonies) {
            String playerName = bettingMoney.getOwnerName();
            MatchResult playerMatchResult = scoreBoard.findPlayerMatchResult(playerName);
            int earning = playerMatchResult.getEarnings(bettingMoney.getValue());
            playersEarnings.put(playerName, earning);
        }
        return playersEarnings;
    }

    private static int calculateDealerEarnings(Map<String, Integer> playersEarnings) {

        return -1 * playersEarnings.values()
                .stream()
                .mapToInt(e -> e)
                .sum();
    }

    public int findPlayerEarning(String playerName) {
        return playersEarnings.get(playerName);
    }

    public Map<String, Integer> getPlayersEarnings() {
        return Collections.unmodifiableMap(playersEarnings);
    }

    public int getDealerEarnings() {
        return dealerEarnings;
    }
}
