package blackjack.domain.betting;

import blackjack.domain.participant.Participant;
import blackjack.domain.state.CardHand;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BettingReferee {

    private final List<BettingResult> bettingResults;

    public BettingReferee(final Participant dealer, final PlayerBettings playerBettings) {
        List<BettingResult> playerBettingResults = getPlayerBettingResultsFrom(playerBettings, dealer);
        BettingResult dealerBettingResult = initDealerResultFrom(dealer, playerBettingResults);

        this.bettingResults = getUnmodifiableOrderedListOf(dealerBettingResult, playerBettingResults);
    }

    private List<BettingResult> getUnmodifiableOrderedListOf(final BettingResult dealerBettingResult,
                                                             final List<BettingResult> playerBettingResults) {
        List<BettingResult> bettingResults = new ArrayList<>();

        bettingResults.add(dealerBettingResult);
        bettingResults.addAll(playerBettingResults);

        return Collections.unmodifiableList(bettingResults);
    }

    private List<BettingResult> getPlayerBettingResultsFrom(final PlayerBettings playerBettings,
                                                            final Participant dealer) {
        return playerBettings.getValue()
                .stream()
                .map(playerBetting -> initPlayerBettingResultFrom(playerBetting, dealer))
                .collect(Collectors.toUnmodifiableList());
    }

    private BettingResult initPlayerBettingResultFrom(final PlayerBetting playerBetting, final Participant dealer) {
        final Participant player = playerBetting.getPlayer();
        final int bettingAmount = playerBetting.getBettingAmount();
        final double bettingYield = getBettingYield(player, dealer);

        final int profit = (int) (bettingYield * bettingAmount);

        return new BettingResult(player, profit);
    }

    private double getBettingYield(Participant player, Participant dealer) {
        final CardHand playerHand = player.getCardHand();
        final CardHand dealerHand = dealer.getCardHand();

        return playerHand.getBettingYieldVersus(dealerHand);
    }

    private BettingResult initDealerResultFrom(final Participant dealer,
                                               final List<BettingResult> playerBettingResults) {
        final int totalPlayerProfit = getTotalProfitOf(playerBettingResults);
        final int dealerProfit = totalPlayerProfit * -1;

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
        return "BettingReferee{" + "bettingResults=" + bettingResults + '}';
    }
}
