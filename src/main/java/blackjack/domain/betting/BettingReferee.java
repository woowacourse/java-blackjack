package blackjack.domain.betting;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BettingReferee {

    private final List<BettingResult> bettingResults;

    public BettingReferee(final Dealer dealer, final PlayerBettings playerBettings) {
        List<BettingResult> playerBettingResults = getPlayerBettingResultsFrom(playerBettings, dealer);
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

    private List<BettingResult> getPlayerBettingResultsFrom(PlayerBettings playerBettings,
                                                            Dealer dealer) {
        return playerBettings.getValue()
                .stream()
                .map(playerBetting -> initPlayerBettingResultFrom(playerBetting, dealer))
                .collect(Collectors.toUnmodifiableList());
    }

    private BettingResult initPlayerBettingResultFrom(final PlayerBetting playerBetting,
                                                      final Dealer dealer) {
        Player player = playerBetting.getPlayer();
        final int bettingAmount = playerBetting.getBettingAmount();

        final int winAmount = player.getDuelResultWith(dealer)
                .getProfitOf(bettingAmount);

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
        return "BettingReferee{" +
                "bettingResults=" + bettingResults +
                '}';
    }
}
