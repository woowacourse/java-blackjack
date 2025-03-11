package blackjack.domain;

import static blackjack.domain.Rule.BLACK_JACK;
import static blackjack.domain.WinningType.DEFEAT;
import static blackjack.domain.WinningType.DRAW;
import static blackjack.domain.WinningType.WIN;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.gambler.Name;

import java.util.Arrays;
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
        WinningType winningType = judgePlayerResult(name);
        if (winningType == WIN) {
            winningResult.put(DEFEAT, winningResult.get(DEFEAT) + 1);
            return;
        }
        if (winningType == DEFEAT) {
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
