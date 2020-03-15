package domain;

import domain.player.Dealer;
import domain.player.Players;
import domain.player.User;

import java.util.HashMap;
import java.util.Map;

public class WinningResult {
    private Map<String, Boolean> winningPlayer;

    public WinningResult(Players players) {
        if (players == null) {
            throw new NullPointerException("결과를 계산할 플레이어가 없습니다.");
        }

        winningPlayer = new HashMap<>();

        Dealer dealer = players.getDealer();
        for (User user : players.getUsers()) {
            winningPlayer.put(user.getName(), DecisionWinner.compareWinner(user, dealer));
        }
    }

    public Map<String, Boolean> getWinningResult() {
        return winningPlayer;
    }
}
