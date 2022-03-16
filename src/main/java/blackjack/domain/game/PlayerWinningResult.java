package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum PlayerWinningResult {

    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0);

    private static final double BLACKJACK_BETTING_PROFIT_RATE = 1.5;

    private final String result;
    private final int bettingProfitRate;

    PlayerWinningResult(String result, int bettingMultiple) {
        this.result = result;
        this.bettingProfitRate = bettingMultiple;
    }

    public static PlayerWinningResult of(Dealer dealer, Player player) {
        if ((dealer.isBlackjack() && !player.isBlackjack()) || player.isBust()) {
            return LOSE;
        }
        if ((!dealer.isBlackjack() && player.isBlackjack()) || dealer.isBust()) {
            return WIN;
        }
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return DRAW;
        }
        return getResultWithScore(dealer, player);
    }

    private static PlayerWinningResult getResultWithScore(Dealer dealer, Player player) {
        if (player.getScore() < dealer.getScore()) {
            return LOSE;
        }
        if (player.getScore() > dealer.getScore()) {
            return WIN;
        }
        return DRAW;
    }

    public PlayerWinningResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }

    public int getBettingProfit(int money, boolean isBlackjack) {
        if (isBlackjack) {
            return (int)(money * BLACKJACK_BETTING_PROFIT_RATE);
        }
        return money * bettingProfitRate;
    }

    public String getResult() {
        return result;
    }
}
