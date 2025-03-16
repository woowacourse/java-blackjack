package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetSystem {

    private static final double BLACK_JACK_PROFIT = 1.5;
    private final Map<Gamer, Long> beforeBetRecord;
    private final Map<Gamer, Long> afterBetRecord;

    public BetSystem() {
        this.beforeBetRecord = new LinkedHashMap<>();
        this.afterBetRecord = new LinkedHashMap<>();
        this.afterBetRecord.put(new Dealer(), 0L);
    }

    public void betting(final Player player, final long betAmount) {
        validateBetAmount(betAmount);
        beforeBetRecord.put(player, betAmount);
        afterBetRecord.put(player, 0L);
    }

    public Map<Gamer, Long> calculateProfit(final Dealer dealer, final List<Player> players) {
        for (Player player : players) {
            MatchResult dealerMatchResult = dealer.getMatchResult(player);
            calculatePlayerWin(dealer, player, dealerMatchResult);
            calculateDealerWin(dealer, player, dealerMatchResult);
            calculatePlayerBlackjackWin(dealer, player, dealerMatchResult);
        }

        return afterBetRecord;
    }

    private void calculatePlayerBlackjackWin(final Dealer dealer, final Player player,
                                             final MatchResult dealerMatchResult) {
        if (dealerMatchResult.equals(MatchResult.BLACKJACK_LOSE)) {
            long betAmount = beforeBetRecord.get(player);
            long blackjackBetAmount = Math.round(betAmount * BLACK_JACK_PROFIT);
            betAmount += blackjackBetAmount;

            long dealerAmount = afterBetRecord.get(dealer);
            dealerAmount -= blackjackBetAmount;

            afterBetRecord.put(player, betAmount);
            afterBetRecord.put(dealer, dealerAmount);
        }
    }

    private void calculateDealerWin(final Dealer dealer, final Player player, final MatchResult dealerMatchResult) {
        if (dealerMatchResult.equals(MatchResult.WIN)) {
            long betAmount = beforeBetRecord.get(player);

            long dealerBetAmount = afterBetRecord.get(dealer);
            dealerBetAmount = dealerBetAmount + betAmount;

            afterBetRecord.put(player, -betAmount);
            afterBetRecord.put(dealer, dealerBetAmount);
        }
    }

    private void calculatePlayerWin(final Dealer dealer, final Player player, final MatchResult dealerMatchResult) {
        if (dealerMatchResult.equals(MatchResult.LOSE)) {
            long betAmount = beforeBetRecord.get(player);

            long dealerBetAmount = afterBetRecord.get(dealer);
            dealerBetAmount = dealerBetAmount - betAmount;

            afterBetRecord.put(player, betAmount);
            afterBetRecord.put(dealer, dealerBetAmount);
        }
    }

    private void validateBetAmount(final double betAmount) {
        if (betAmount < 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 0원 이상이어야 합니다.");
        }
    }
}
