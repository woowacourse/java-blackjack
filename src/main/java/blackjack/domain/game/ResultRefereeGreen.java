package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultRefereeGreen {

    private final BettingResult dealerBettingResult;
    private final List<BettingResult> playerBettingResult;

    public ResultRefereeGreen(Dealer dealer, List<Player> players) {
        this.playerBettingResult = players.stream()
                .map(player -> initPlayerBettingResultFrom(player, dealer))
                .collect(Collectors.toUnmodifiableList());
        this.dealerBettingResult = initDealerResultFrom(dealer);
    }

    private BettingResult initPlayerBettingResultFrom(final Player player, final Dealer dealer) {
        ResultType playerResult = player.getDuelResultWith(dealer);
        int bettingAmount = player.getBettingAmount();
        if (playerResult == ResultType.DRAW) {
            bettingAmount = 0;
        }
        if (playerResult == ResultType.LOSE) {
            bettingAmount *= -1;
        }
        return new BettingResult(player, bettingAmount);
    }

    private BettingResult initDealerResultFrom(final Dealer dealer) {
        int playerBettingSum = playerBettingResult.stream()
                .mapToInt(BettingResult::getValue)
                .sum();
        int dealerBettingSum = playerBettingSum * -1;

        return new BettingResult(dealer, dealerBettingSum);
    }

    public List<BettingResult> getResults() {
        List<BettingResult> results = new ArrayList<>();

        results.add(dealerBettingResult);
        results.addAll(playerBettingResult);

        return results;
    }
}
