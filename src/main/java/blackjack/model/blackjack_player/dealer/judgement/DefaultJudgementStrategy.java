package blackjack.model.blackjack_player.dealer.judgement;

import blackjack.model.blackjack_player.dealer.Dealer;
import blackjack.model.blackjack_player.player.Player;

public final class DefaultJudgementStrategy implements JudgementStrategy {

    private static final float BLACKJACK_REWARD_RATE = 1.5f;

    @Override
    public boolean isDraw(final Dealer dealer, final Player player) {
        if (isDealerOrPlayerBust(dealer, player)) {
            return false;
        }
        if (dealer.isBlackjack()) {
            return player.isBlackjack();
        }
        return dealer.getOptimalPoint() == player.getOptimalPoint();
    }

    @Override
    public boolean isDealerWin(final Dealer dealer, final Player player) {
        if (dealer.isBust()) {
            return false;
        }
        if (isDealerOnlyBlackjack(dealer, player)) {
            return true;
        }
        if (isPlayerOnlyBlackjack(dealer, player)) {
            return false;
        }
        if (isPlayerOnlyBust(dealer, player)) {
            return true;
        }

        return dealer.getOptimalPoint() > player.getOptimalPoint();
    }

    private boolean isDealerOrPlayerBust(final Dealer dealer, final Player player) {
        return dealer.isBust() || player.isBust();
    }

    private boolean isDealerOnlyBlackjack(final Dealer dealer, final Player player) {
        return dealer.isBlackjack() && !player.isBlackjack();
    }

    private boolean isPlayerOnlyBlackjack(final Dealer dealer, final Player player) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private boolean isPlayerOnlyBust(final Dealer dealer, final Player player) {
        return player.isBust() && !dealer.isBust();
    }

    @Override
    public int calculatePlayerReward(final Player player) {
        int bettingMoney = player.getBettingMoney();
        if (player.isBlackjack()) {
            return Math.round(bettingMoney * BLACKJACK_REWARD_RATE);
        }
        return bettingMoney;
    }
}
