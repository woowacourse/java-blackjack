package balckjack.domain;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Referee {

    private static final int BLACKJACK_SCORE = 21;

    private final List<Result> results;

    public Referee(Participant dealer, Players players) {
        results = generateResult(dealer, players);

    }

    private List<Result> generateResult(Participant dealer, Players players) {
        int dealerScore = dealer.getCardDeck().calculateScore().getValue();
        List<Integer> playerScores = players.getPlayers().stream()
            .map((player) -> player.getCardDeck().calculateScore().getValue()).collect(
                Collectors.toList());
        return playerScores.stream().map((score) -> compareScore(dealerScore, score))
            .collect(Collectors.toList());
    }

    private Result compareScore(int dealerScore, int playerScore) {
        if (playerScore > BLACKJACK_SCORE) {
            return Result.LOSE;
        }
        if (dealerScore > BLACKJACK_SCORE || playerScore > dealerScore) {
            return Result.WIN;
        }
        if (playerScore == dealerScore) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    public Map<String, Long> countDealerResult(List<Result> results) {
        return results.stream().collect(groupingBy(Result::getResult, counting()));
    }

    public List<Result> getResults() {
        return results;
    }
}
