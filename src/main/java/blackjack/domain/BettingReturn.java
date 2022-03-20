package blackjack.domain;

import blackjack.constant.MatchResult;
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

    public static BettingReturn of(ScoreBoard scoreBoard, List<BettingMoney> bettingMonies) {
        Map<String, Integer> playersReturns = calculatePlayerReturn(scoreBoard, bettingMonies);
        int dealerReturn = calculateDealerReturn(playersReturns);
        return new BettingReturn(playersReturns, dealerReturn);
    }

    private static Map<String, Integer> calculatePlayerReturn(ScoreBoard scoreBoard, List<BettingMoney> bettingMonies) {
        Map<String, Integer> playersReturns = new LinkedHashMap<>();
        for (BettingMoney bettingMoney : bettingMonies) {
            String playerName = bettingMoney.getOwnerName();
            MatchResult playerMatchResult = scoreBoard.findPlayerMatchResult(playerName);
            int earning = playerMatchResult.getEarnings(bettingMoney.getValue());
            playersReturns.put(playerName, earning);
        }
        return playersReturns;
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
