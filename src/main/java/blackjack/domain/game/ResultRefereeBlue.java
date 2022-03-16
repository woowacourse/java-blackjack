package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO: should be deleted
public class ResultRefereeBlue {

    private final ResultStatistics dealerResultStat;
    private final List<ResultStatistics> playerResultStats;

    public ResultRefereeBlue(final Dealer dealer, final List<Player> players) {
        this.dealerResultStat = ResultStatistics.of(dealer);
        this.playerResultStats = players.stream()
                .map(player -> initPlayerResultStatFrom(player, dealer))
                .collect(Collectors.toUnmodifiableList());
    }

    private ResultStatistics initPlayerResultStatFrom(final Player player, final Dealer dealer) {
        ResultStatistics playerResultStat = ResultStatistics.of(player);
        ResultType playerResult = player.getDuelResultWith(dealer);

        incrementResultsFrom(playerResult, playerResultStat);

        return playerResultStat;
    }

    private void incrementResultsFrom(final ResultType playerResult,
                                      final ResultStatistics playerResultStat) {
        dealerResultStat.incrementCountOf(ResultType.getOppositeOf(playerResult));
        playerResultStat.incrementCountOf(playerResult);
    }

    public List<ResultStatistics> getResults() {
        List<ResultStatistics> results = new ArrayList<>();

        results.add(dealerResultStat);
        results.addAll(playerResultStats);

        return results;
    }

    @Override
    public String toString() {
        return "ResultReferee{" +
                "dealerResultStat=" + dealerResultStat +
                ", playerResultStats=" + playerResultStats +
                '}';
    }
}
