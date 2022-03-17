package blackjack.domain.player;

import blackjack.domain.result.Result;

public class Money {

    public static final int MIN_BET = 1000;
    private final int bet;

    public Money(int bet) {
        checkPositive(bet);
        checkDivisibleMinBet(bet);
        this.bet = bet;
    }

    private void checkPositive(int bet) {
        if (bet < 0) {
            throw new IllegalArgumentException("[ERROR} 베팅 금액은 양수여야합니다.");
        }
    }

    private void checkDivisibleMinBet(int bet) {
        if (bet % MIN_BET != 0) {
            throw new IllegalArgumentException("[ERROR} 베팅 금액은 " + MIN_BET + "원으로 나누어져야 합니다.");
        }
    }

    public int getRevenue(Result result) {
        return (int) (bet * result.getYield());
    }
}
