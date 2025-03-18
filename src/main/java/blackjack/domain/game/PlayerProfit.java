package blackjack.domain.game;

import blackjack.domain.value.Nickname;

public final class PlayerProfit {

    private final Nickname nickname;
    private final int profit;

    public PlayerProfit(Nickname nickname, int profit) {
        this.nickname = nickname;
        this.profit = profit;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public int getProfit() {
        return profit;
    }
}
