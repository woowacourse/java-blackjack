package blackjack.domain.result;

import blackjack.domain.player.Player;
import java.util.HashMap;
import java.util.Map;

public enum ResultType {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    ResultType(String value) {
        this.value = value;
    }

    public static Map<Player, ResultType> judgeGameResult(Player p1, Player p2) {
        Map<Player, ResultType> result = new HashMap<>();
        int p1Score = p1.calculateScore();
        int p2Score = p2.calculateScore();

        if (p1Score > 21 && p2Score > 21) {
            result.put(p1, DRAW);
            result.put(p2, DRAW);
            return result;
        }
        if (p1Score > 21) {
            result.put(p1, LOSE);
            result.put(p2, WIN);
            return result;
        }
        if (p2Score > 21) {
            result.put(p1, WIN);
            result.put(p2, LOSE);
            return result;
        }

        if (p1Score > p2Score) {
            result.put(p1, WIN);
            result.put(p2, LOSE);
        }
        if (p1Score == p2Score) {
            result.put(p1, DRAW);
            result.put(p2, DRAW);
        }
        if (p1Score < p2Score) {
            result.put(p1, LOSE);
            result.put(p2, WIN);
        }
        return result;
    }
}
