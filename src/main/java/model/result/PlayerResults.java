package model.result;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.game.CardsScore;

public class PlayerResults {

    private final Map<String, ResultStatus> results;

    private PlayerResults(Map<String, ResultStatus> results) {
        this.results = Collections.unmodifiableMap(results);
    }

    public static PlayerResults from(GameResult gameResult) {
        CardsScore dealerScore = gameResult.getDealerScore();
        Map<String, CardsScore> playerScores = gameResult.getPlayerScores();

        return playerScores.keySet()
            .stream()
            .collect(collectingAndThen(
                toMap(
                    playerName -> playerName,
                    playerName -> decideResultStatus(playerScores.get(playerName), dealerScore)
                ),
                PlayerResults::new)
            );
    }

    public static ResultStatus decideResultStatus(CardsScore player, CardsScore dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (player.isBurst() && dealer.isBurst() || playerScore == dealerScore) {
            return model.result.ResultStatus.PUSH;
        }
        if (player.isBurst() || (dealer.isNotBurst() && dealerScore > playerScore)) {
            return model.result.ResultStatus.LOOSE;
        }
        return model.result.ResultStatus.WIN;
    }

    public ResultStatus findPlayerResult(String playerName) {
        return results.get(playerName);
    }

    public Set<String> playerNames() {
        return results.keySet();
    }

    public List<ResultStatus> resultStatuses() {
        return results.values()
            .stream()
            .toList();
    }
}
