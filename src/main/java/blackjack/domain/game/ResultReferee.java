package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultReferee {

    private final ResultStatistics dealerResult;
    private final List<ResultStatistics> playerResults;

    public ResultReferee(Dealer dealer, List<Player> players) {
        this.dealerResult = ResultStatistics.of(dealer);
        this.playerResults = players.stream()
                .map(player -> addDuelResult(player, dealer))
                .collect(Collectors.toUnmodifiableList());
    }

    private ResultStatistics addDuelResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return initPlayerResultOf(player, ResultType.LOSE);
        }

        if (dealer.isBust()) {
            return initPlayerResultOf(player, ResultType.WIN);
        }

        return addNoBustDuelResult(player, dealer.getCurrentScore());
    }

    private ResultStatistics addNoBustDuelResult(Player player, Score dealerScore) {
        int compareResult = player.getCurrentScore().compareTo(dealerScore);

        if (compareResult > 0) {
            return initPlayerResultOf(player, ResultType.WIN);
        }
        if (compareResult < 0) {
            return initPlayerResultOf(player, ResultType.LOSE);
        }
        return initPlayerResultOf(player, ResultType.DRAW);
    }

    private ResultStatistics initPlayerResultOf(Player player, ResultType playerResultType) {
        ResultStatistics playerResult = ResultStatistics.of(player);
        incrementResultsByPlayerResultOf(playerResultType, playerResult);

        return playerResult;
    }

    private void incrementResultsByPlayerResultOf(ResultType playerType, ResultStatistics playerResult) {
        dealerResult.incrementCountOf(ResultType.getOppositeTypeOf(playerType));
        playerResult.incrementCountOf(playerType);
    }

    public List<ResultStatistics> getResults() {
        List<ResultStatistics> results = new ArrayList<>(List.of(dealerResult));
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
