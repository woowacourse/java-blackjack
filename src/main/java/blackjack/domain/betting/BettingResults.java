package blackjack.domain.betting;

import blackjack.domain.participant.Participant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BettingResults {

    private final List<BettingResult> value;

    public BettingResults(final Participant dealer, final PlayerBettings playerBettings) {
        List<BettingResult> playerBettingResults = initAllPlayerResults(playerBettings, dealer);
        BettingResult dealerBettingResult = initDealerResult(dealer, playerBettingResults);

        this.value = toList(dealerBettingResult, playerBettingResults);
    }

    private List<BettingResult> toList(final BettingResult dealerBettingResult,
                                       final List<BettingResult> playerBettingResults) {
        List<BettingResult> bettingResults = new ArrayList<>();

        bettingResults.add(dealerBettingResult);
        bettingResults.addAll(playerBettingResults);

        return Collections.unmodifiableList(bettingResults);
    }

    private List<BettingResult> initAllPlayerResults(final PlayerBettings playerBettings,
                                                     final Participant dealer) {
        return playerBettings.getValue()
                .stream()
                .map(playerBetting -> initPlayerResult(playerBetting, dealer))
                .collect(Collectors.toUnmodifiableList());
    }

    private BettingResult initPlayerResult(final PlayerBetting playerBetting,
                                           final Participant dealer) {
        final Participant player = playerBetting.getPlayer();
        final int profit = playerBetting.profit(dealer);

        return new BettingResult(player, profit);
    }

    private BettingResult initDealerResult(final Participant dealer,
                                           final List<BettingResult> playerBettingResults) {
        final int totalPlayerProfit = profitSumOf(playerBettingResults);
        final int dealerProfit = totalPlayerProfit * -1;

        return new BettingResult(dealer, dealerProfit);
    }

    private int profitSumOf(List<BettingResult> playerBettingResult) {
        return playerBettingResult.stream()
                .mapToInt(BettingResult::getProfit)
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
