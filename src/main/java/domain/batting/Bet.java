package domain.batting;

import domain.result.GameResult;

public class Bet {

    public static final int MAXIMUM_BET = 100_000;

    private final int money;

    private Bet(int money) {
        validatePositive(money);
        validateMaximumBet(money);
        this.money = money;
    }

    public static Bet of(int money) {
        return new Bet(money);
    }

    private void validatePositive(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("배팅 금액은 음수나 0이 될 수 없습니다.");
        }
    }

    private void validateMaximumBet(int money) {
        if (money > MAXIMUM_BET) {
            throw new IllegalArgumentException("배팅 가능 금액은 최대 10만원 입니다.");
        }
    }

    public int calculateProfit(GameResult result) {
        if (result == GameResult.BLACKJACK_WIN) {
            return (int) (money * (1.5));
        }
        if (result == GameResult.LOSE) {
            return money * (-1);
        }
        if (result == GameResult.WIN) {
            return money;
        }
        return 0;
    }
}
