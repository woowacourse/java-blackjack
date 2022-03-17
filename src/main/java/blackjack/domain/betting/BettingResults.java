package blackjack.domain.betting;

import blackjack.domain.participant.Participant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BettingResults {

    private final List<BettingResult> value;

    public BettingResults(final Participant dealer, final PlayerBettings playerBettings) {
        List<BettingResult> playerBettingResults = initAllPlayerBettingResults(playerBettings, dealer);
        BettingResult dealerBettingResult = initDealerResultFrom(dealer, playerBettingResults);

        this.value = getUnmodifiableOrderedListOf(dealerBettingResult, playerBettingResults);
    }

    private List<BettingResult> getUnmodifiableOrderedListOf(final BettingResult dealerBettingResult,
                                                             final List<BettingResult> playerBettingResults) {
        List<BettingResult> bettingResults = new ArrayList<>();

        bettingResults.add(dealerBettingResult);
        bettingResults.addAll(playerBettingResults);

        return Collections.unmodifiableList(bettingResults);
    }

    private List<BettingResult> initAllPlayerBettingResults(final PlayerBettings playerBettings,
                                                            final Participant dealer) {
        return playerBettings.getValue()
                .stream()
                .map(playerBetting -> initPlayerBettingResult(playerBetting, dealer))
                .collect(Collectors.toUnmodifiableList());
    }

    private BettingResult initPlayerBettingResult(final PlayerBetting playerBetting, final Participant dealer) {
        final Participant player = playerBetting.getPlayer();
        final int profit = playerBetting.getProfit(dealer);

        return new BettingResult(player, profit);
    }

    private BettingResult initDealerResultFrom(final Participant dealer,
                                               final List<BettingResult> playerBettingResults) {
        final int totalPlayerProfit = getProfitSumOf(playerBettingResults);
        final int dealerProfit = totalPlayerProfit * -1;

        return new BettingResult(dealer, dealerProfit);
    }

    private int getProfitSumOf(List<BettingResult> playerBettingResult) {
        return playerBettingResult.stream()
                .mapToInt(BettingResult::getMoneyOutcome)
                .sum();
    }

    public List<BettingResult> getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BettingReferee{" + "bettingResults=" + value + '}';
    }
}
