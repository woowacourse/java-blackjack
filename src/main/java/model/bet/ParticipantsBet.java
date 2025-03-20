package model.bet;

import exception.IllegalBlackjackInputException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.cards.PlayerCards;
import model.result.BettingOdds;
import model.result.BettingResults;
import model.result.GameResult;
import model.result.GameResults;

public class ParticipantsBet {

    private final Map<String, Integer> playersBet;

    public ParticipantsBet(final Map<String, Integer> playersBet) {
        validateParticipantBetsCount(playersBet);
        this.playersBet = playersBet;
    }

    public BettingResults calculateBettingResults(final GameResults gameResults) {
        return new BettingResults(playersBet.keySet().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        name -> calculateBettingResultByName(name, gameResults)
                ))
        );
    }

    private int calculateBettingResultByName(final String name, final GameResults gameResults) {
        GameResult gameResult = gameResults.getGameResultByName(name);
        BettingOdds bettingOdds = BettingOdds.from(gameResult);
        return (int) bettingOdds.multiple(playersBet.get(name));
    }

    private void validateParticipantBetsCount(final Map<String, Integer> playersBet) {
        if (playersBet.isEmpty()) {
            throw new IllegalBlackjackInputException("플레이어는 1명 이상 있어야 합니다.");
        }
    }
}
