package blackjack.domain;

import blackjack.domain.gambler.Name;
import java.util.HashMap;
import java.util.Map;

public class WinningDiscriminator {
    private final int dealerScore;
    private final Map<Name, Integer> playerScores;

    public WinningDiscriminator(int dealerScore, Map<Name, Integer> playerScores) {
        this.dealerScore = dealerScore;
        this.playerScores = playerScores;
    }

    public Map<String, Integer> judgeDealerResult() {
        Map<String, Integer> winningResult = new HashMap<>(Map.of("승", 0, "패", 0, "무", 0));

        for (Integer playerScore : playerScores.values()) {
            if (playerScore > 21) {
                winningResult.put("승", winningResult.get("승") + 1);
                continue;
            }

            if (dealerScore > 21) {
                winningResult.put("패", winningResult.get("패") + 1);
                continue;
            }

            if (playerScore > dealerScore) {
                winningResult.put("패", winningResult.get("패") + 1);
                continue;
            }

            if (playerScore < dealerScore) {
                winningResult.put("승", winningResult.get("승") + 1);
                continue;
            }
            winningResult.put("무", winningResult.get("무") + 1);
        }

        return winningResult;
    }
}
