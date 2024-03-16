package gameResult;

import participant.dealer.Dealer;
import participant.player.BetMoney;
import participant.player.Player;

public class PlayerWin implements GameResult {

    private static final double MONEY_RETURN_PERCENT = 1;

    @Override
    public boolean isCorrespondentResult(Player player, Dealer dealer) {
        return isDealerBust(dealer) || isPlayerScoreOverDealerScore(player, dealer);
    }

    @Override
    public int profitMoney(BetMoney betMoney) {
        return betMoney.betMoneyResult(MONEY_RETURN_PERCENT);
    }

    private boolean isDealerBust(Dealer dealer) {
        return dealer.isBust();
    }

    private boolean isPlayerScoreOverDealerScore(Player player, Dealer dealer) {
        return player.getCardScore() > dealer.getCardScore();
    }
}
