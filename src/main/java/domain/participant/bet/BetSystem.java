package domain.participant.bet;

import domain.match.MatchResult;
import domain.participant.Dealer;
import domain.participant.Gamer;
import domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetSystem {

    private static final long SET_PROFIT = 0L;

    private final Map<Gamer, BetAmount> bettingRecord;
    private final Map<Gamer, Long> profitRecord;

    public BetSystem() {
        this.bettingRecord = new LinkedHashMap<>();
        this.profitRecord = new LinkedHashMap<>();
        this.profitRecord.put(new Dealer(), SET_PROFIT);
    }

    public void betting(final Player player, final long betAmount) {
        bettingRecord.put(player, BetAmount.from(betAmount));
        profitRecord.put(player, SET_PROFIT);
    }

    public Map<Gamer, Long> calculateProfit(final Dealer dealer, final List<Player> players) {
        for (Player player : players) {
            MatchResult dealerMatchResult = dealer.getMatchResult(player);
            calculatePlayerWin(dealer, player, dealerMatchResult);
            calculateDealerWin(dealer, player, dealerMatchResult);
            calculatePlayerBlackjackWin(dealer, player, dealerMatchResult);
        }

        return profitRecord;
    }

    private void calculatePlayerBlackjackWin(final Dealer dealer, final Player player,
                                             final MatchResult dealerMatchResult) {
        if (isPlayerBlackjack(dealerMatchResult)) {
            BetAmount betAmount = bettingRecord.get(player);
            Long dealerProfit = profitRecord.get(dealer);

            Long blackjackProfit = betAmount.calculateBlackjackProfit();

            profitRecord.put(player, betAmount.plus(blackjackProfit));
            profitRecord.put(dealer, dealerProfit - blackjackProfit);
        }
    }

    private boolean isPlayerBlackjack(final MatchResult dealerMatchResult) {
        return getDealerMatchResult(dealerMatchResult).equals(MatchResult.DEALER_BLACKJACK_LOSE);
    }

    private MatchResult getDealerMatchResult(final MatchResult dealerMatchResult) {
        return dealerMatchResult;
    }

    private void calculateDealerWin(final Dealer dealer, final Player player, final MatchResult dealerMatchResult) {
        if (isDealerWin(dealerMatchResult)) {
            BetAmount betAmount = bettingRecord.get(player);
            Long dealerProfit = profitRecord.get(dealer);
            Long playerProfit = profitRecord.get(player);

            profitRecord.put(player, playerProfit - (betAmount.getValue()));
            profitRecord.put(dealer, dealerProfit + (betAmount.getValue()));
        }
    }

    private boolean isDealerWin(final MatchResult dealerMatchResult) {
        return dealerMatchResult.equals(MatchResult.DEALER_WIN);
    }

    private void calculatePlayerWin(final Dealer dealer, final Player player, final MatchResult dealerMatchResult) {
        if (isDealerLose(dealerMatchResult)) {
            BetAmount betAmount = bettingRecord.get(player);
            Long playerProfit = profitRecord.get(player);
            Long dealerProfit = profitRecord.get(dealer);

            profitRecord.put(player, betAmount.plus(playerProfit));
            profitRecord.put(dealer, dealerProfit - betAmount.getValue());
        }
    }

    private boolean isDealerLose(final MatchResult dealerMatchResult) {
        return dealerMatchResult.equals(MatchResult.DEALER_LOSE);
    }

}
