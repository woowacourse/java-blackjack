package blackjack.domain.game;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Referee {

    private static final int BURST_SCORE = 22;
    private static final double BLACKJACK_BONUS = 1.5;

    public List<Result> judgeResult(Participant dealer, Players players) {
        return players.getPlayers().stream()
                .map(player -> getResult(dealer, player))
                .collect(Collectors.toList());
    }

    private Result getResult(Participant dealer, Player player) {
        if (hasBlackJack(dealer, player)) {
            return processBlackJack(dealer, player);
        }
        return compareScore(dealer.calculateScore(), player.calculateScore());
    }

    private boolean hasBlackJack(Participant dealer, Player player) {
        return dealer.isBlackJack() || player.isBlackJack();
    }

    private Result processBlackJack(Participant dealer, Player player) {
        if (dealer.isBlackJack() && player.isBlackJack()) {
            return Result.DRAW;
        }
        if (dealer.isBlackJack()) {
            return Result.LOSE;
        }
        return Result.BLACKJACK;
    }

    private Result compareScore(int dealerScore, int playerScore) {
        if (playerScore >= BURST_SCORE) {
            return Result.LOSE;
        }
        if (playerScore == dealerScore) {
            return Result.DRAW;
        }
        if (playerScore > dealerScore || dealerScore >= BURST_SCORE) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    public Map<String, Long> countDealerResult(List<Result> results) {
        return results.stream().collect(groupingBy(Result::getResult, counting()));
    }

    public List<Integer> calculatePlayersProfit(List<Result> results, Players players) {
        List<Integer> profits = new ArrayList<>();

        for (int index = 0; index < results.size(); index++) {
            profits.add(calculate(results.get(index), players.getPlayers().get(index).getAmount().getValue()));
        }
        return profits;
    }

    private int calculate(Result result, int amount) {
        if (result == Result.BLACKJACK) {
            return (int) (amount * BLACKJACK_BONUS);
        }
        if (result == Result.WIN) {
            return amount;
        }
        if (result == Result.DRAW) {
            return 0;
        }
        return -amount;
    }
}
