package blackjack.domain;

import static blackjack.domain.Round.BLACK_JACK;
import static blackjack.view.WinningType.DEFEAT;
import static blackjack.view.WinningType.DRAW;
import static blackjack.view.WinningType.WIN;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.gambler.Name;
import blackjack.view.WinningType;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

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
        Map<WinningType, Integer> winningResult = WinningType.createWinningResult();
        for (final Integer playerScore : playerScores.values()) {
            countDealerWinning(playerScore, winningResult);
        }
        return winningResult;
    }

    private void countDealerWinning(final int playerScore, final Map<WinningType, Integer> winningResult) {
        BiFunction<WinningType, Integer, Integer> plusCount = (key, count) -> count + 1;
        if (playerScore > BLACK_JACK) {
            winningResult.computeIfPresent(WIN, plusCount);
            return;
        }
        if (dealerScore > BLACK_JACK) {
            winningResult.computeIfPresent(DEFEAT, plusCount);
            return;
        }
        if (playerScore > dealerScore) {
            winningResult.computeIfPresent(DEFEAT, plusCount);
            return;
        }
        if (playerScore < dealerScore) {
            winningResult.computeIfPresent(WIN, plusCount);
            return;
        }
        winningResult.computeIfPresent(DRAW, plusCount);
    }

    public WinningType judgePlayerResult(final Name name) {
        int playerScore = playerScores.get(name);
        if (playerScore > BLACK_JACK) {
            return DEFEAT;
        }
        if (dealerScore > BLACK_JACK) {
            return WIN;
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
