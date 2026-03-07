package model;

import static model.GameStatus.DRAW;
import static model.GameStatus.LOSE;
import static model.GameStatus.WIN;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlayerResult {

    private final Map<String, GameStatus> result;

    private PlayerResult(Map<String, GameStatus> result) {
        this.result = result;
    }

    public static PlayerResult initResult() {
        return new PlayerResult(new HashMap<>());
    }

    public void judgeByPlayer(Dealer dealer, Player player) {
        int playerScore = player.calculateTotalScore();
        int dealerScore = dealer.calculateTotalScore();

        result.put(player.getName(), decide(playerScore, dealerScore));
    }

    private GameStatus decide(int playerScore, int dealerScore) {
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
