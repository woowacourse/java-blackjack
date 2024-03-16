package gameResult;

import participant.dealer.Dealer;
import participant.player.BetMoney;
import participant.player.Player;

public class PlayerBlackJack implements GameResult {

    private static final double MONEY_RETURN_PERCENT = 1.5;

    @Override
    public boolean isCorrespondentResult(Player player, Dealer dealer) {
        return player.isBlackJack();
    }

    @Override
    public int profitMoney(BetMoney betMoney) {
        return betMoney.betMoneyResult(MONEY_RETURN_PERCENT);
    }
}
