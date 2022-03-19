package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum PlayerWinningResult {

    WIN(1),
    LOSE(-1),
    DRAW(0);

    private static final double BLACKJACK_BETTING_PROFIT_RATE = 1.5;

    private final int bettingProfitRate;

    PlayerWinningResult(int bettingMultiple) {
        this.bettingProfitRate = bettingMultiple;
    }

    public static PlayerWinningResult of(Dealer dealer, Player player) {
        if (isDealerOnlyBlackjack(dealer, player) || player.isBust()) {
            return LOSE;
        }
        if (isPlayerOnlyBlackjack(dealer, player) || dealer.isBust()) {
            return WIN;
        }
        if (isDealerAndPlayerBlackjack(dealer, player)) {
            return DRAW;
        }
        return getResultWithScore(dealer, player);
    }

    private static boolean isDealerOnlyBlackjack(Dealer dealer, Player player) {
        return dealer.isBlackjack() && !player.isBlackjack();
    }

    private static boolean isPlayerOnlyBlackjack(Dealer dealer, Player player) {
        return !dealer.isBlackjack() && player.isBlackjack();
    }

    private static boolean isDealerAndPlayerBlackjack(Dealer dealer, Player player) {
        return dealer.isBlackjack() && player.isBlackjack();
    }

    private static PlayerWinningResult getResultWithScore(Dealer dealer, Player player) {
        if (player.getScore() < dealer.getScore()) {
            return LOSE;
        }
        if (player.getScore() > dealer.getScore()) {
            return WIN;
        }
        return DRAW;
    }

    public int getBettingProfit(int money, boolean isBlackjack) {
        if (isBlackjack) {
            return (int)(money * BLACKJACK_BETTING_PROFIT_RATE);
        }
        return money * bettingProfitRate;
    }
}
