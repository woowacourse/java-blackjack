package domain.result;

import domain.Money;
import domain.WinningStatus;
import domain.betting.Betting;

public record Result(Betting betting, WinningStatus winningStatus) {

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
}
