package blackjack.object;

import blackjack.object.gambler.Name;

import java.util.*;

import static blackjack.object.WinningType.*;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class WinningDiscriminator {
    public static final int BLACK_JACK = 21;
    private final Map<Name, Integer> gamblerScores;

    public WinningDiscriminator(final Map<Name, Integer> gamblerScores, final Map<Name, Integer> bettingRecords) {
        this.gamblerScores = gamblerScores;
    }

    public Map<Name, WinningType> judgePlayersResult() {
        return gamblerScores.keySet()
                .stream()
                .filter(name -> !name.equals(Name.getDealerName()))
                .collect(toMap(identity(), this::judgePlayerResult));
    }

    public Map<WinningType, Integer> judgeDealerResult() {
        Map<WinningType, Integer> winningResult = createWinningResult();
        List<Name> playerNames = new ArrayList<>(gamblerScores.keySet());
        playerNames.remove(Name.getDealerName());

        for (final Name playerName : playerNames) {
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
        int playerScore = gamblerScores.get(name);
        int dealerScore = gamblerScores.get(Name.getDealerName());

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
