package model.judgement;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import model.paticipant.Player;

public class PlayerResult {

    private final Map<Player, ResultStatus> result;

    public PlayerResult(Map<Player, ResultStatus> result) {
        this.result = new LinkedHashMap<>(result);
    }

    public int countByStatus(ResultStatus resultStatus) {
        return (int) result.values()
                .stream()
                .filter(status -> status == resultStatus)
                .count();
    }

    public Map<Player, Profit> calculateProfits() {
        Map<Player, Profit> profits = new LinkedHashMap<>();
        result.forEach((player, status) -> profits.put(player, player.calculateProfit(status)));
        return profits;
    }

    public Profit calculateDealerProfit() {
        return calculateProfits().values()
                .stream()
                .reduce(Profit.ZERO, (sum, profit) -> sum.add(profit.negate()));
    }

    public Map<Player, ResultStatus> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
