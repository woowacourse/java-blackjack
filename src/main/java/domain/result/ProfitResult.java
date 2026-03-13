package domain.result;

import domain.participant.BetMap;

import java.util.Map;

public class ProfitResult {
    private final ProfitMap playerProfitMap;

    private ProfitResult(BlackjackResult blackjackResult, BetMap betMap) {
        this.playerProfitMap = new ProfitMap();
        calculateProfits(blackjackResult, betMap);
    }

    public static ProfitResult from(BlackjackResult blackjackResult, BetMap betMap) {
        return new ProfitResult(blackjackResult, betMap);
    }

    private void calculateProfits(BlackjackResult blackjackResult, BetMap betMap) {
        for (Map.Entry<String, MatchCase> entry : blackjackResult.getPlayerResultMap().entrySet()) {
            String playerName = entry.getKey();
            MatchCase matchCase = entry.getValue();
            playerProfitMap.addProfitOf(playerName, betMap.calculateProfit(playerName, matchCase));
        }
    }

    public Map<String, Long> getPlayerProfitMap() {
        return playerProfitMap.getMap();
    }

    public long getDealerBenefit() {
        return (-1) * playerProfitMap.sumProfits();
    }
}
