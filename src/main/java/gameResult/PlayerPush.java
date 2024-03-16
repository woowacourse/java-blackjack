package gameResult;

import participant.dealer.Dealer;
import participant.player.BetMoney;
import participant.player.Player;

public class PlayerPush implements GameResult {

    private static final double MONEY_RETURN_PERCENT = 0;

    @Override
    public boolean isCorrespondentResult(Player player, Dealer dealer) {
        return bothBlackJack(player, dealer) || bothSameScore(player, dealer);
    }

    @Override
    public int profitMoney(BetMoney betMoney) {
        return betMoney.betMoneyResult(MONEY_RETURN_PERCENT);
    }

    private boolean bothBlackJack(Player player, Dealer dealer) {
        return player.isBlackJack() && dealer.isBlackJack();
    }

    private boolean bothSameScore(Player player, Dealer dealer) {
        return player.getCardScore() == dealer.getCardScore();
    }
}
