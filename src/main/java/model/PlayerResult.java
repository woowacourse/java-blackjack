package model;

import static model.GameStatus.DRAW;
import static model.GameStatus.LOSE;
import static model.GameStatus.WIN;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerResult {

    private static final int BUST_LIMIT = 21;
    private final Map<Player, GameStatus> result;

    private PlayerResult(Map<Player, GameStatus> result) {
        this.result = result;
    }

    public static PlayerResult judgeByPlayer(Dealer dealer, List<Player> players) {
        Map<Player, GameStatus> result = new HashMap<>();

        int dealerScore = dealer.calculateTotalScore();
        players.forEach(player -> {
            int playerScore = player.calculateTotalScore();
            result.put(player, decide(playerScore, dealerScore));
        });

        return new PlayerResult(result);
    }

    private static GameStatus decide(int playerScore, int dealerScore) {
        if (playerScore > BUST_LIMIT) {
            return LOSE;
        }
        if (dealerScore > BUST_LIMIT || playerScore > dealerScore) {
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

    public Map<Player, GameStatus> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
