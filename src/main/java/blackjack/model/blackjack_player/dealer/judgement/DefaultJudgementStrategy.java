package blackjack.model.blackjack_player.dealer.judgement;

import blackjack.model.blackjack_player.dealer.Dealer;
import blackjack.model.blackjack_player.player.Player;

public final class DefaultJudgementStrategy implements JudgementStrategy {

    private static final float BLACKJACK_REWARD_RATE = 1.5f;

    @Override
    public boolean isDraw(final Dealer dealer, final Player player) {
        if (player.isBust() || dealer.isBust()) {
            return false;
        }
        if (dealer.isBlackjack()) {
            return player.isBlackjack();
        }
        return dealer.getOptimalPoint() == player.getOptimalPoint();
    }

    @Override
    public boolean isDealerWin(final Dealer dealer, final Player player) {
        if (player.isBlackjack() || dealer.isBust()) {
            return false;
        }
        if (player.isBust() || dealer.isBlackjack()) {
            return true;
        }
        return dealer.getOptimalPoint() > player.getOptimalPoint();
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
