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
                .map(player -> initPlayerResultOf(player, getDuelResultFrom(player, dealer)))
                .collect(Collectors.toUnmodifiableList());
    }

    private ResultType getDuelResultFrom(final Player player, final Dealer dealer) {
        if (dealer.isBlackjack()) {
            return getBlackjackDealerDuelResult(player);
        }
        if (player.isBust() || dealer.isBust()) {
            return getBustDuelResult(player);
        }
        if (player.isBlackjack()) {
            return ResultType.WIN;
        }
        return addNoBustDuelResult(player, dealer.getScore());
    }

    private ResultType getBlackjackDealerDuelResult(final Player player) {
        if (player.isBlackjack()) {
            return ResultType.DRAW;
        }
        return ResultType.LOSE;
    }

    private ResultType getBustDuelResult(final Player player) {
        if (player.isBust()) {
            return ResultType.LOSE;
        }
        return ResultType.WIN;
    }

    private ResultType addNoBustDuelResult(final Player player, final Score dealerScore) {
        int compareResult = player.getScore().compareTo(dealerScore);

        if (compareResult > 0) {
            return ResultType.WIN;
        }
        if (compareResult < 0) {
            return ResultType.LOSE;
        }
        return ResultType.DRAW;
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
