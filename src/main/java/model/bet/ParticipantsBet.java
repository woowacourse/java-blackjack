package model.bet;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.result.GameResult;
import model.result.GameResults;

public class ParticipantsBet {

    private final Map<String, Integer> playersBet;

    public ParticipantsBet(final Map<String, Integer> playersBet) {
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
        GameResult gameResult =  gameResults.getGameResultByName(name);
        BettingOdds bettingOdds = BettingOdds.from(gameResult);
        return (int) bettingOdds.multiple(playersBet.get(name));
    }
}
