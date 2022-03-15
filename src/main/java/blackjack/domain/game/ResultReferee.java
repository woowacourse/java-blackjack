package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultReferee {

    private final ResultStatistics dealerResult;
    private final List<ResultStatistics> playerResults;

    public ResultReferee(final Dealer dealer, final List<Player> players) {
        this.dealerResult = ResultStatistics.of(dealer);
        this.playerResults = players.stream()
                .map(player -> addDuelResult(player, dealer))
                .collect(Collectors.toUnmodifiableList());
    }

    private ResultStatistics addDuelResult(final Player player, final Dealer dealer) {
        if (dealer.isBlackjack()) {
            return addBlackjackDealerDuelResult(player);
        }
        if (player.isBust() || dealer.isBust()) {
            return getBustDuelResult(player);
        }
        if (player.isBlackjack()) {
            return initPlayerResultOf(player, ResultType.WIN);
        }
        return addNoBustDuelResult(player, dealer.getCurrentScore());
    }

    private ResultStatistics addBlackjackDealerDuelResult(final Player player) {
        if (player.isBlackjack()) {
            return initPlayerResultOf(player, ResultType.DRAW);
        }
        return initPlayerResultOf(player, ResultType.LOSE);
    }

    private ResultStatistics getBustDuelResult(final Player player) {
        if (player.isBust()) {
            return initPlayerResultOf(player, ResultType.LOSE);
        }
        return initPlayerResultOf(player, ResultType.WIN);
    }

    private ResultStatistics addNoBustDuelResult(final Player player, final Score dealerScore) {
        int compareResult = player.getCurrentScore().compareTo(dealerScore);

        if (compareResult > 0) {
            return initPlayerResultOf(player, ResultType.WIN);
        }
        if (compareResult < 0) {
            return initPlayerResultOf(player, ResultType.LOSE);
        }
        return initPlayerResultOf(player, ResultType.DRAW);
    }

    private ResultStatistics initPlayerResultOf(final Player player, final ResultType playerResultType) {
        ResultStatistics playerResult = ResultStatistics.of(player);
        incrementResultsByPlayerResultOf(playerResultType, playerResult);

        return playerResult;
    }

    private void incrementResultsByPlayerResultOf(final ResultType playerType,
                                                  final ResultStatistics playerResult) {
        dealerResult.incrementCountOf(ResultType.getOppositeTypeOf(playerType));
        playerResult.incrementCountOf(playerType);
    }

    public List<ResultStatistics> getResults() {
        List<ResultStatistics> results = new ArrayList<>();

        results.add(dealerResult);
        results.addAll(playerResults);

        return results;
    }

    @Override
    public String toString() {
        return "ResultReferee{" +
                "dealerResult=" + dealerResult +
                ", playerResults=" + playerResults +
                '}';
    }
}
