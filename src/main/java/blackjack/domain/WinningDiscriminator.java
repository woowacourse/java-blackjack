package blackjack.domain;

import static blackjack.domain.Rule.BLACK_JACK;
import static blackjack.domain.WinningType.DEFEAT;
import static blackjack.domain.WinningType.DRAW;
import static blackjack.domain.WinningType.WIN;
import static blackjack.domain.WinningType.createWinningResult;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.gambler.Name;

import java.util.Map;

public class WinningDiscriminator {
    private final int dealerScore;
    private final Map<Name, Integer> playerScores;

    public WinningDiscriminator(final int dealerScore, final Map<Name, Integer> playerScores) {
        this.dealerScore = dealerScore;
        this.playerScores = playerScores;
    }

    public Map<Name, WinningType> judgePlayersResult() {
        return playerScores.keySet()
                .stream()
                .collect(toMap(identity(), this::judgePlayerResult));
    }

    public Map<WinningType, Integer> judgeDealerResult() {
        Map<WinningType, Integer> winningResult = createWinningResult();
        for (final Integer playerScore : playerScores.values()) {
            countDealerWinning(playerScore, winningResult);
        }
        return winningResult;
    }

    private void countDealerWinning(final int playerScore, final Map<WinningType, Integer> winningResult) {
        if (playerScore > BLACK_JACK) {
            winningResult.put(WIN, winningResult.get(WIN) + 1);
            return;
        }
        if (dealerScore > BLACK_JACK) {
            winningResult.put(DEFEAT, winningResult.get(DEFEAT) + 1);
            return;
        }
        if (playerScore > dealerScore) {
            winningResult.put(DEFEAT, winningResult.get(DEFEAT) + 1);
            return;
        }
        if (playerScore < dealerScore) {
            winningResult.put(WIN, winningResult.get(WIN) + 1);
            return;
        }
        winningResult.put(DRAW, winningResult.get(DRAW) + 1);
    }

    private WinningType judgePlayerResult(final Name name) {
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
