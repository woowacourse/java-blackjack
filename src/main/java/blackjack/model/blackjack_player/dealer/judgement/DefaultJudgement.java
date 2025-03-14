package blackjack.model.blackjack_player.dealer.judgement;

import blackjack.model.blackjack_player.dealer.Dealer;
import blackjack.model.blackjack_player.player.Player;

public final class DefaultJudgement implements Judgement {

    private static final float BLACKJACK_REWARD_RATE = 1.5f;

    @Override
    public boolean isDraw(Dealer dealer, Player player) {
        if (player.isBust() || dealer.isBust() || player.isBlackjack() || dealer.isBlackjack()) {
            return false;
        }
        return dealer.getOptimalPoint() == player.getOptimalPoint();
    }

    @Override
    public boolean isDealerWin(Dealer dealer, Player player) {
        if (player.isBlackjack() || dealer.isBust()) {
            return false;
        }
        if (player.isBust() || dealer.isBlackjack()) {
            return true;
        }
        return dealer.getOptimalPoint() > player.getOptimalPoint();
    }

    @Override
    public int calculatePlayerReward(Player player) {
        int bettingMoney = player.getBettingMoney();
        if (player.isBlackjack()) {
            return Math.round(bettingMoney * BLACKJACK_REWARD_RATE);
        }
        return bettingMoney;
    }
}
