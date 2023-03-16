package domain.result;

import domain.game.GameStatus;
import domain.user.Playable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StatusResult {
    
    private final Map<Playable, GameStatus> resultMap = new HashMap<>();
    
    public void accumulate(Playable player, GameStatus gameStatus) {
        this.resultMap.put(player, gameStatus);
    }
    
    public Map<GameStatus, Integer> getDealerResult() {
        HashMap<GameStatus, Integer> dealerResult = new HashMap<>();
        dealerResult.put(GameStatus.WIN, 0);
        dealerResult.put(GameStatus.DRAW, 0);
        dealerResult.put(GameStatus.LOSE, 0);
        for (GameStatus gameStatus : this.resultMap.values()) {
            if (gameStatus == GameStatus.WIN || gameStatus == GameStatus.WIN_BLACKJACK) {
                dealerResult.put(GameStatus.LOSE, dealerResult.get(GameStatus.LOSE) + 1);
            }
            if (gameStatus == GameStatus.DRAW) {
                dealerResult.put(GameStatus.DRAW, dealerResult.get(GameStatus.DRAW) + 1);
            }
            if (gameStatus == GameStatus.LOSE) {
                dealerResult.put(GameStatus.WIN, dealerResult.get(GameStatus.WIN) + 1);
            }
        }
        return dealerResult;
    }
    
    public GameStatus getPlayerResult(Playable player) {
        return this.resultMap.get(player);
    }
    
    public Map<Playable, GameStatus> getResultMap() {
        return Collections.unmodifiableMap(this.resultMap);
    }
}
