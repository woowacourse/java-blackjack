package model;

import static model.GameStatus.DRAW;
import static model.GameStatus.LOSE;
import static model.GameStatus.WIN;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerResult {

    private final Map<String, GameStatus> result;

    private PlayerResult(Map<String, GameStatus> result) {
        this.result = result;
    }

    public static PlayerResult judgeByPlayer(Dealer dealer, List<Player> players) {
        Map<String, GameStatus> result = new HashMap<>();

        int dealerScore = dealer.calculateTotalScore();
        players.forEach(player -> {
            int playerScore = player.calculateTotalScore();
            result.put(player.getName(), decide(playerScore, dealerScore));
        });

        return new PlayerResult(result);
    }

    private static GameStatus decide(int playerScore, int dealerScore) {
        if (playerScore > 21) {
            return LOSE;
        }
        if (dealerScore > 21 || playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }

    public int countByStatus(GameStatus gameStatus) {
        return (int) result.values()
                .stream()
                .filter(status -> status == gameStatus)
                .count();
    }

    public Map<String, GameStatus> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
