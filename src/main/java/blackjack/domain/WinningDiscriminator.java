package blackjack.domain;

import blackjack.domain.gambler.Name;

import java.util.Arrays;
import java.util.Map;

import static blackjack.domain.WinningType.*;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class WinningDiscriminator {
    public static final int BLACK_JACK = 21;
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
        for (final Name playerName : playerScores.keySet()) {
            countDealerWinning(playerName, winningResult);
        }
        return winningResult;
    }

    private Map<WinningType, Integer> createWinningResult() {
        return Arrays.stream(WinningType.values())
                .collect(toMap(identity(), type -> 0));
    }

    private void countDealerWinning(final Name name, final Map<WinningType, Integer> winningResult) {
        WinningType dealerResult = judgePlayerResult(name).reverse();
        winningResult.put(dealerResult, winningResult.get(dealerResult) + 1);
    }

    private WinningType judgePlayerResult(final Name name) {
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
