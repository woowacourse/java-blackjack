package blackjack.domain;

import blackjack.common.ErrorMessage;

public class Wallet {

    private static final int MAXIMUM_BETTING_MONEY = 100_000_000;

    private int currentBetMoney;
    private int balance;

    private Wallet(int currentBetMoney, int balance) {
        this.currentBetMoney = currentBetMoney;
        this.balance = balance;
    }

    public static Wallet create() {
        return new Wallet(0, 0);
    }

    public void betting(int money) {
        validBetMoney(money);
        this.currentBetMoney = money;
        this.balance = money;
    }

    public void receiveBlackjackBonus() {
        this.balance += (int) (this.balance * 1.5);
    }

    public void calculate(GameResultType gameResultType) {
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

    private void validBetMoney(int betMoney) {
        if (betMoney > MAXIMUM_BETTING_MONEY) {
            throw new IllegalArgumentException(ErrorMessage.MAXIMUM_BETTING_MONEY.getMessage());
        }

        if (betMoney <= 0) {
            throw new IllegalArgumentException(ErrorMessage.BETTING_MONEY_IS_POSITIVE.getMessage());
        }
    }
}
