package blackjack.model;

import blackjack.model.participant.BettingAmount;
import blackjack.model.participant.Player;
import java.util.Map;

public class BettingBoard {
    private static final double BLACKJACK_PROFIT_RATE = 1.5;

    private final Map<Player, BettingAmount> moneys;

    public BettingBoard(final Map<Player, BettingAmount> moneys) {
        this.moneys = moneys;
    }

    public BettingAmount giveWinnerMoneyByBlackJack(final Player player) {
        BettingAmount bettingAmount = moneys.get(player);
        return bettingAmount.multiple(BLACKJACK_PROFIT_RATE);
    }

    public BettingAmount giveTieMoneyByBlackJack(final Player player) {
        BettingAmount bettingAmount = moneys.get(player);
        // TODO: 딜러 계산을 위해 초기값에서 빼야함
        return bettingAmount;
    }

    public BettingAmount giveWinnerMoneyWhenDealerBust(final Player player) {
        BettingAmount bettingAmount = moneys.get(player);
        return bettingAmount;
    }

    public BettingAmount payMoneyWhenPlayerBust(final Player player) {
        BettingAmount bettingAmount = moneys.get(player);
        return bettingAmount.payAll();
    }
}
