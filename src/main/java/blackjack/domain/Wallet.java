package blackjack.domain;

import blackjack.common.ErrorMessage;

public class Wallet {

    private static final int MAXIMUM_BETTING_MONEY = 100_000_000;
    private static final double BLACKJACK_BONUS_RATE = 1.5;

    private final int currentBetMoney;
    private int balance;

    private Wallet(int currentBetMoney, int balance) {
        this.currentBetMoney = currentBetMoney;
        this.balance = balance;
    }

    public static Wallet bet(int money) {
        validBetMoney(money);
        return new Wallet(money, money);
    }

    private static void validBetMoney(int betMoney) {
        if (betMoney > MAXIMUM_BETTING_MONEY) {
            throw new IllegalArgumentException(ErrorMessage.MAXIMUM_BETTING_MONEY.getMessage());
        }

        if (betMoney <= 0) {
            throw new IllegalArgumentException(ErrorMessage.BETTING_MONEY_IS_POSITIVE.getMessage());
        }
    }

    public void calculate(GameResultType gameResultType, Hand hand) {
        if (gameResultType == GameResultType.WIN && hand.isBlackjack()) {
            this.balance += (int) (this.balance * BLACKJACK_BONUS_RATE);
            return;
        }
        if (gameResultType == GameResultType.WIN) {
            this.balance += currentBetMoney;
        }

        if (gameResultType == GameResultType.LOSE) {
            this.balance -= currentBetMoney;
        }
    }

    public int getRevenue() {
        return this.balance - this.currentBetMoney;
    }
}
