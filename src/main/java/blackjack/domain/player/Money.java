package blackjack.domain.player;

import blackjack.domain.result.Result;

public class Money {

    private final int bet;

    public Money(int bet) {
        this.bet = bet;
    }

    public int getRevenue(Result result) {
        return (int) (bet * result.getYield());
    }
}
