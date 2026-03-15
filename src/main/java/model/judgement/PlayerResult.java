package model.judgement;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import model.paticipant.BettingPlayer;
import model.paticipant.Player;

public record PlayerResult(Map<Player, ResultStatus> result) {

    public PlayerResult(Map<Player, ResultStatus> result) {
        this.result = Collections.unmodifiableMap(new LinkedHashMap<>(result));
    }

    public DealerResult calculateDealerResult() {
        int dealerWinCount = countByStatus(ResultStatus.LOSE);
        int dealerLoseCount = countByStatus(ResultStatus.WIN) + countByStatus(ResultStatus.BLACKJACK);
        int dealerDrawCount = countByStatus(ResultStatus.DRAW);
        return new DealerResult(dealerWinCount, dealerLoseCount, dealerDrawCount);
    }

    public Map<Player, Profit> calculateProfits() {
        Map<Player, Profit> profits = new LinkedHashMap<>();
        result.forEach((player, status) -> {
            if (player instanceof BettingPlayer bettingPlayer) {
                profits.put(bettingPlayer, bettingPlayer.calculateProfit(status));
            }
        });
        return profits;
    }

    public Profit calculateDealerProfit() {
        return calculateProfits().values()
                .stream()
                .reduce(Profit.ZERO, (sum, profit) -> sum.add(profit.negate()));
    }

    private int countByStatus(ResultStatus resultStatus) {
        return (int) result.values()
                .stream()
                .filter(status -> status == resultStatus)
                .count();
    }
}
