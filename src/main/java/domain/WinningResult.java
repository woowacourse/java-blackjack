package domain;

import domain.player.Dealer;
import domain.player.Users;
import domain.player.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class WinningResult {
    private Map<String, Boolean> winningPlayer;
    private Map<String, Double> winningResult;

    public WinningResult(Users users) {
        if (users == null) {
            throw new NullPointerException("결과를 계산할 플레이어가 없습니다.");
        }

        winningPlayer = new LinkedHashMap<>();
        winningResult = new LinkedHashMap<>();

        Dealer dealer = users.getDealer();
        for (Player player : users.getPlayer()) {
            winningPlayer.put(player.getName(), DecisionWinner.compareWinner(player, dealer));
        }
        for (Player player : users.getPlayer()) {
            winningResult.put(player.getName(), ProfitCalculator.getProfit(player, dealer));
        }
    }

    public Map<String, Boolean> getWinningPlayer() {
        return winningPlayer;
    }

    public Map<String, Double> getWinningResult() {
        return winningResult;
    }
}
