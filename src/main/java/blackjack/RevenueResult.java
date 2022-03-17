package blackjack;

import blackjack.constant.MatchResult;
import blackjack.domain.BettingMoney;
import blackjack.domain.ScoreBoard;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RevenueResult {

    private final Map<String, Integer> playersEarnings = new LinkedHashMap<>();
    private int dealerEarnings = 0;

    private RevenueResult(ScoreBoard scoreBoard, List<BettingMoney> bettingMonies) {
        calculatePlayerEarnings(scoreBoard, bettingMonies);
        calculateDealerEarnings();
    }

    public static RevenueResult of(ScoreBoard scoreBoard, List<BettingMoney> bettingMonies) {
        return new RevenueResult(scoreBoard, bettingMonies);
    }

    private void calculatePlayerEarnings(ScoreBoard scoreBoard, List<BettingMoney> bettingMonies) {
        for (BettingMoney bettingMoney : bettingMonies) {
            String playerName = bettingMoney.getOwnerName();
            MatchResult playerMatchResult = scoreBoard.findPlayerMatchResult(playerName);
            int earning = playerMatchResult.getEarnings(bettingMoney.getValue());
            playersEarnings.put(playerName, earning);
        }
    }

    private void calculateDealerEarnings() {
        int playersEarnings = this.playersEarnings.values().stream().mapToInt(e -> e).sum();
        dealerEarnings -= playersEarnings;
    }

    public int findPlayerEarning(String playerName) {
        return playersEarnings.get(playerName);
    }

    public Map<String, Integer> getPlayersEarnings() {
        return playersEarnings;
    }

    public int getDealerEarnings() {
        return dealerEarnings;
    }
}
