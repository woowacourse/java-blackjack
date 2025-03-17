package blackjack.model.blackjack_player.player.betting;

import blackjack.model.blackjack_player.Hand;
import blackjack.model.blackjack_player.dealer.result.Result;

public final class Betting {

    private static final float BLACKJACK_REWARD_RATE = 1.5f;

    private final int bettingMoney;
    private int balance;

    public Betting(final int bettingMoney, final int balance) {
        validateNegativeMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
        this.balance = balance;
    }

    public static Betting bet(final int bettingMoney) {
        return new Betting(bettingMoney, bettingMoney);
    }

    private void earn(final int money) {
        validateNegativeMoney(money);
        balance += money;
    }

    private void lose() {
        balance = 0;
    }

    private void validateNegativeMoney(final int money) {
        if (money < 0) {
            throw new IllegalArgumentException("금액은 음수가 될 수 없습니다.");
        }
    }

    public int getBalance() {
        return balance;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }

    public void applyResult(final Result result, final Hand hand) {
        if (result == Result.DEALER_WIN) {
            lose();
            return;
        }
        if (result == Result.DEALER_LOSE) {
            earn(calculateReward(hand));
        }
    }

    private int calculateReward(final Hand hand) {
        if (hand.isBlackjack()) {
            return Math.round(bettingMoney * BLACKJACK_REWARD_RATE);
        }
        return bettingMoney;
    }
}
