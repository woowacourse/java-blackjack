package player;

import static blackjackGame.Result.LOSE;
import static blackjackGame.Result.TIE;
import static blackjackGame.Result.WIN;

import java.util.EnumMap;

import blackjackGame.Result;

public class DealerResult {
    private final EnumMap<Result, Integer> dealerResult = new EnumMap<>(Result.class) {
        {
            put(WIN, 0);
            put(TIE, 0);
            put(LOSE, 0);
        }
    };

    public void addWin() {
        dealerResult.put(WIN, dealerResult.get(WIN) + 1);
    }

    public void addTie() {
        dealerResult.put(TIE, dealerResult.get(TIE) + 1);
    }

    public void addLose() {
        dealerResult.put(LOSE, dealerResult.get(LOSE) + 1);
    }
}
