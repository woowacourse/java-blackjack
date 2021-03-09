package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public class GameResult {
    private final List<Integer> resultCounts;
    private final List<Result> playersResult;

    public GameResult(final Dealer dealer, final List<Player> players) {
        resultCounts = findResultCounts(dealer, players);
        playersResult = findPlayersResult(dealer, players);
    }

    private List<Integer> findResultCounts(final Dealer dealer, final List<Player> players) {
        final List<Integer> resultCounts = new ArrayList<>();
        resultCounts.add(findResult(dealer, players, Result.WIN));
        resultCounts.add(findResult(dealer, players, Result.LOSE));
        resultCounts.add(findResult(dealer, players, Result.DRAW));
        return resultCounts;
    }

    private int findResult(final Dealer dealer, final List<Player> players, final Result result) {
        return (int) players.stream()
                .filter(player -> findDealerResult(dealer, player)
                        .isSame(result)).count();
    }

    private Result findDealerResult(final Dealer dealer, final Player player) {
        final int dealerScore = dealer.calculateScore();
        final int playerScore = player.calculateScore();
        if (dealerScore == playerScore) {
            return Result.DRAW;
        }
        if (player.isBust() || playerScore < dealerScore && !dealer.isBust()) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private List<Result> findPlayersResult(final Dealer dealer, final List<Player> players) {
        final List<Result> playersResult = new ArrayList<>();
        for (final Player player : players) {
            playersResult.add(findPlayerResult(dealer, player));
        }
        return playersResult;
    }

    private Result findPlayerResult(final Dealer dealer, final Player player) {
        final int dealerScore = dealer.calculateScore();
        final int playerScore = player.calculateScore();
        if (dealerScore == playerScore) {
            return Result.DRAW;
        }
        if (player.isBust() || playerScore < dealerScore && !dealer.isBust()) {
            return Result.LOSE;
        }
        return Result.WIN;
    }

    public List<Result> getPlayersResult() {
        return playersResult;
    }

    public List<Integer> getResultCounts() {
        return resultCounts;
    }
}
