package domain;

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
        if (dealerMatchResult.equals(MatchResult.BLACKJACK_LOSE)) {
            BetAmount betAmount = bettingRecord.get(player);
            Long dealerProfit = profitRecord.get(dealer);

            Long blackjackProfit = betAmount.calculateBlackjackProfit();

            profitRecord.put(player, betAmount.plus(blackjackProfit));
            profitRecord.put(dealer, dealerProfit - blackjackProfit);
        }
    }

    private void calculateDealerWin(final Dealer dealer, final Player player, final MatchResult dealerMatchResult) {
        if (dealerMatchResult.equals(MatchResult.WIN)) {
            BetAmount betAmount = bettingRecord.get(player);
            Long dealerProfit = profitRecord.get(dealer);
            Long playerProfit = profitRecord.get(player);

            profitRecord.put(player, playerProfit - (betAmount.getValue()));
            profitRecord.put(dealer, dealerProfit + (betAmount.getValue()));
        }
    }

    private void calculatePlayerWin(final Dealer dealer, final Player player, final MatchResult dealerMatchResult) {
        if (dealerMatchResult.equals(MatchResult.LOSE)) {
            BetAmount betAmount = bettingRecord.get(player);
            Long playerProfit = profitRecord.get(player);
            Long dealerProfit = profitRecord.get(dealer);

            profitRecord.put(player, betAmount.plus(playerProfit));
            profitRecord.put(dealer, dealerProfit - betAmount.getValue());
        }
    }

}
