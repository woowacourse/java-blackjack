package domain.result;

import domain.Dealer;
import domain.Money;
import domain.Player;
import domain.WinningStatus;
import domain.betting.Betting;

public record Result(Betting betting, WinningStatus winningStatus) {

    public Player getPlayer() {
        return betting.player();
    }

    public Money getProfit() {
        if (winningStatus == WinningStatus.WIN) {
            return betting.money();
        }
        if (winningStatus == WinningStatus.BLACKJACK_WIN) {
            return betting.money().multiply(1.5);
        }
        if (winningStatus == WinningStatus.LOSE) {
            return betting.money().multiply(-1);
        }
        return betting.money().multiply(0);
    }

    public static Result of(Dealer dealer, Betting betting) {
        WinningStatus winningStatus = calculateWinningStatus(dealer, betting.player());
        return new Result(betting, winningStatus);
    }

    private static WinningStatus calculateWinningStatus(Dealer dealer, Player player) {
        if (player.isBlackjackAtFirst() && !dealer.isBlackjackAtFirst()) {
            return WinningStatus.BLACKJACK_WIN;
        }
        if (player.isBust()) {
            return WinningStatus.LOSE;
        }
        if (dealer.isBust()) {
            return WinningStatus.WIN;
        }
        if (dealer.getScore() > player.getScore()) {
            return WinningStatus.LOSE;
        }
        if (dealer.getScore() == player.getScore()) {
            return WinningStatus.DRAW;
        }
        return WinningStatus.WIN;
    }
}
