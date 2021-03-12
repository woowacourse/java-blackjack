package blackjack.domain.game;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Money;
import blackjack.domain.player.Player;

public enum WinOrLose {
    WIN_BLACK_JACK("승", 1.5),
    WIN_NORMAL("승", 1.0),
    LOSE("패", -1.0),
    DRAW("무", 0);

    private final String symbol;
    private final double yield;

    WinOrLose(final String symbol, final double yield) {
        this.symbol = symbol;
        this.yield = yield;
    }

    public static WinOrLose calculateGamblerWinOrNot(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return checkGamblerIsBlackJack(player);
        }
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return DRAW;
        }
        return compareScore(dealer, player);
    }

    private static WinOrLose compareScore(Dealer dealer, Player player) {
        if (dealer.isBiggerThan(player)) {
            return LOSE;
        }
        if (dealer.isLessThan(player)) {
            return checkGamblerIsBlackJack(player);
        }
        return DRAW;
    }

    private static WinOrLose checkGamblerIsBlackJack(Player player) {
        if (player.isBlackJack()) {
            return WIN_BLACK_JACK;
        }
        return WIN_NORMAL;
    }

    public Money calculateProfit(Money money) {
        return money.calculateProfit(yield);
    }

    public String getSymbol() {
        return symbol;
    }

    public double getYield() {
        return yield;
    }
}
