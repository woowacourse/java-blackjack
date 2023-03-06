package blackjack.domain;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Referee {

    private static final int BURST_CODE = -1;

    public List<Result> judgeResult(Participant dealer, Players players) {
        int dealerScore = dealer.calculateScore();
        List<Integer> playerScores = mapPlayerScores(players);
        return mapResult(dealerScore, playerScores);
    }

    private List<Result> mapResult(int dealerScore, List<Integer> playerScores) {
        return playerScores.stream()
                .map((score) -> compareScore(dealerScore, score))
                .collect(Collectors.toList());
    }

    private static List<Integer> mapPlayerScores(Players players) {
        return players.getPlayers().stream()
                .map(Participant::calculateScore)
                .collect(Collectors.toList());
    }

    public Map<String, Long> countDealerResult(List<Result> results) {
        return results.stream().collect(groupingBy(Result::getResult, counting()));
    }

    private Result compareScore(int dealerScore, int playerScore) {
        if (playerScore == BURST_CODE) {
            return Result.LOSE;
        }
        if (playerScore > dealerScore) {
            return Result.WIN;
        }
        if (playerScore == dealerScore) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }


}
