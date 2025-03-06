package blackjack.domain;

import static java.util.function.Function.identity;

import blackjack.domain.gambler.Name;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<Name, String> judgePlayersResult() {
        return playerScores.keySet().stream()
                .collect(Collectors.toMap(identity(), this::judgePlayerResult));
    }

    private String judgePlayerResult(Name name) {
        int playerScore = playerScores.get(name);
        if (playerScore > 21) {
            return "패";
        }
        if (playerScore > dealerScore) {
            return "승";
        }
        if (playerScore < dealerScore) {
            return "패";
        }
        return "무";
    }
}
