package domain.result;

import domain.betting.Betting;
import domain.money.Profit;
import domain.participant.Dealer;
import domain.participant.Player;

// todo 배팅결과, 게임 결과를 분리
public record Result(Betting betting, WinningStatus winningStatus) {

    public Player getPlayer() {
        return betting.player();
    }

    public Profit getProfit() {
        return Profit.calculateProfit(betting.bettingMoney(), winningStatus);
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
