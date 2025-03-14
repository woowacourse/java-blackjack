package blackjack.domain.game;

public final class PlayerProfit {

    private final String nickname;
    private final int profit;

    public PlayerProfit(String nickname, int profit) {
        this.nickname = nickname;
        this.profit = profit;
    }

    public String getNickname() {
        return nickname;
    }

    public int getProfit() {
        return profit;
    }
}
