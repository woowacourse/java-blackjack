package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class ResultReferee {

    private final Dealer dealer;
    private final ResultStatistics dealerResult;
    private final List<Player> players;
    private final List<ResultStatistics> playerResults = new ArrayList<>();

    public ResultReferee(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
        this.dealerResult = ResultStatistics.of(dealer);
    }

    public List<ResultStatistics> initAndGetGameResults() {
        if (dealer.getCurrentScore().toInt() > Score.BLACKJACK) {
            return getResultsOnDealerBust();
        }

        return getAllDuelResults();
    }

    private List<ResultStatistics> getResultsOnDealerBust() {
        players.stream()
                .map(player -> initPlayerResultOf(player, ResultType.WIN))
                .forEach(playerResults::add);

        return getResults();
    }

    private List<ResultStatistics> getAllDuelResults() {
        for (Player player : players) {
            getAndAddDuelResult(player);
        }

        return getResults();
    }

    private boolean getAndAddDuelResult(Player player) {
        Score playerScore = player.getCurrentScore();
        int compareResult = playerScore.compareTo(dealer.getCurrentScore());

        if (playerScore.toInt() > Score.BLACKJACK || compareResult < 0) {
            return playerResults.add(initPlayerResultOf(player, ResultType.LOSE));
        }
        if (compareResult > 0) {
            return playerResults.add(initPlayerResultOf(player, ResultType.WIN));
        }
        return playerResults.add(initPlayerResultOf(player, ResultType.DRAW));
    }

    private List<ResultStatistics> getResults() {
        List<ResultStatistics> results = new ArrayList<>(List.of(dealerResult));
        results.addAll(playerResults);
        return results;
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
}
