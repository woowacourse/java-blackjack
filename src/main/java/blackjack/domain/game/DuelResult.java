package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum DuelResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final double bettingYield;

    DuelResult(double bettingYield) {
        this.bettingYield = bettingYield;
    }

    public static DuelResult of(final Score score, final Score targetScore) {
        int compareResult = score.compareTo(targetScore);

        if (compareResult > 0) {
            return DuelResult.WIN;
        }
        if (compareResult < 0) {
            return DuelResult.LOSE;
        }
        return DuelResult.DRAW;
    }

    public static DuelResult blackjackOrBustDuelOf(final Player player, final Dealer dealer) {
        if (isOnlyPlayerBlackjackOf(player, dealer)) {
            return DuelResult.BLACKJACK_WIN;
        }
        if (isOnlyDealerBustOf(player, dealer)) {
            return DuelResult.WIN;
        }
        if (isBothBlackjackWith(player, dealer)) {
            return DuelResult.DRAW;
        }
        return DuelResult.LOSE;
    }

    private static boolean isOnlyPlayerBlackjackOf(final Player player, final Dealer dealer) {
        return !dealer.isBlackjack() && player.isBlackjack();
    }

    private static boolean isOnlyDealerBustOf(final Player player, final Dealer dealer) {
        return dealer.isBust() && !player.isBust();
    }

    private static boolean isBothBlackjackWith(final Player player, final Dealer dealer) {
        return dealer.isBlackjack() && player.isBlackjack();
    }

    public int getProfitOf(int bettingAmount) {
        return (int) (bettingAmount * this.bettingYield);
    }
}
