package blackjack.domain;

import static blackjack.domain.Rule.BLACK_JACK;
import static blackjack.view.WinningType.DEFEAT;
import static blackjack.view.WinningType.DRAW;
import static blackjack.view.WinningType.WIN;
import static blackjack.view.WinningType.createWinningResult;
import static java.util.function.Function.identity;

import blackjack.domain.gambler.Name;
import blackjack.view.WinningType;
import java.util.Map;
import java.util.stream.Collectors;

public class WinningDiscriminator {
    private final int dealerScore;
    private final Map<Name, Integer> playerScores;

    public WinningDiscriminator(int dealerScore, Map<Name, Integer> playerScores) {
        this.dealerScore = dealerScore;
        this.playerScores = playerScores;
    }

    public Map<WinningType, Integer> judgeDealerResult() {
        Map<WinningType, Integer> winningResult = createWinningResult();

        for (Integer playerScore : playerScores.values()) {
            if (playerScore > BLACK_JACK) {
                winningResult.put(WIN, winningResult.get(WIN) + 1);
                continue;
            }

            if (dealerScore > BLACK_JACK) {
                winningResult.put(DEFEAT, winningResult.get(DEFEAT) + 1);
                continue;
            }

            if (playerScore > dealerScore) {
                winningResult.put(DEFEAT, winningResult.get(DEFEAT) + 1);
                continue;
            }

            if (playerScore < dealerScore) {
                winningResult.put(WIN, winningResult.get(WIN) + 1);
                continue;
            }
            winningResult.put(DRAW, winningResult.get(DRAW) + 1);
        }

        return winningResult;
    }

    public Map<Name, WinningType> judgePlayersResult() {
        return playerScores.keySet().stream()
                .collect(Collectors.toMap(identity(), this::judgePlayerResult));
    }

    private WinningType judgePlayerResult(Name name) {
        int playerScore = playerScores.get(name);
        if (playerScore > BLACK_JACK) {
            return DEFEAT;
        }
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return DEFEAT;
        }
        return DRAW;
    }
}
