package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ResultRefereeGreen {

    private final List<BettingResult> bettingResults;

    public ResultRefereeGreen(final Dealer dealer, final List<Player> players) {
        List<BettingResult> playerBettingResults = getPlayerBettingResultsFrom(dealer, players);
        BettingResult dealerBettingResult = initDealerResultFrom(
                dealer, getTotalProfitOf(playerBettingResults));

        this.bettingResults = initOrderedBettingResultsOf(dealerBettingResult, playerBettingResults);
    }

    private List<BettingResult> initOrderedBettingResultsOf(BettingResult dealerBettingResult,
                                                            List<BettingResult> playerBettingResults) {
        List<BettingResult> bettingResults = new ArrayList<>();

        bettingResults.add(dealerBettingResult);
        bettingResults.addAll(playerBettingResults);

        return Collections.unmodifiableList(bettingResults);
    }

    private List<BettingResult> getPlayerBettingResultsFrom(Dealer dealer, List<Player> players) {
        return players.stream()
                .map(player -> initPlayerBettingResultFrom(player, dealer))
                .collect(Collectors.toUnmodifiableList());
    }

    private BettingResult initPlayerBettingResultFrom(final Player player, final Dealer dealer) {
        final ResultType playerResult = player.getDuelResultWith(dealer);
        final int bettingAmount = player.getBettingAmount();

        final int winAmount = playerResult.getProfitOf(bettingAmount);

        return new BettingResult(player, winAmount);
    }

    private BettingResult initDealerResultFrom(final Dealer dealer, final int totalPlayerProfit) {
        int dealerProfit = totalPlayerProfit * -1;

        return new BettingResult(dealer, dealerProfit);
    }

    private int getTotalProfitOf(List<BettingResult> playerBettingResult) {
        return playerBettingResult.stream()
                .mapToInt(BettingResult::getMoneyOutcome)
                .sum();
    }

    public List<BettingResult> getResults() {
        return bettingResults;
    }

    @Override
    public String toString() {
        return "ResultRefereeGreen{" +
                "bettingResults=" + bettingResults +
                '}';
    }
}
