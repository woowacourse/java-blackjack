package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetSystem {

    private static final double BLACK_JACK_PROFIT = 1.5;
    private final Map<Gamer, Integer> beforeBetRecord;
    private final Map<Gamer, Integer> afterBetRecord;

    public BetSystem() {
        this.beforeBetRecord = new LinkedHashMap<>();
        this.afterBetRecord = new LinkedHashMap<>();
        this.afterBetRecord.put(new Dealer(), 0);
    }

    public void betting(final Player player, final int betAmount) {
        validateBetAmount(betAmount);
        beforeBetRecord.put(player, betAmount);
        afterBetRecord.put(player, 0);
    }

    public Map<Gamer, Integer> calculateProfit(final Dealer dealer, final List<Player> players) {
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
            int betAmount = beforeBetRecord.get(player);
            int blackjackBetAmount = Math.toIntExact(Math.round(betAmount * BLACK_JACK_PROFIT));
            betAmount += blackjackBetAmount;

            int dealerAmount = afterBetRecord.get(dealer);
            dealerAmount -= blackjackBetAmount;

            afterBetRecord.put(player, betAmount);
            afterBetRecord.put(dealer, dealerAmount);
        }
    }

    private void calculateDealerWin(final Dealer dealer, final Player player, final MatchResult dealerMatchResult) {
        if (dealerMatchResult.equals(MatchResult.WIN)) {
            int betAmount = beforeBetRecord.get(player);

            int dealerBetAmount = afterBetRecord.get(dealer);
            dealerBetAmount = dealerBetAmount + betAmount;

            afterBetRecord.put(player, -betAmount);
            afterBetRecord.put(dealer, dealerBetAmount);
        }
    }

    private void calculatePlayerWin(final Dealer dealer, final Player player, final MatchResult dealerMatchResult) {
        if (dealerMatchResult.equals(MatchResult.LOSE)) {
            int betAmount = beforeBetRecord.get(player);

            int dealerBetAmount = afterBetRecord.get(dealer);
            dealerBetAmount = dealerBetAmount - betAmount;

            afterBetRecord.put(player, betAmount);
            afterBetRecord.put(dealer, dealerBetAmount);
        }
    }

    private void validateBetAmount(final int betAmount) {
        if (betAmount < 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 0원 이상이어야 합니다.");
        }
    }
}
