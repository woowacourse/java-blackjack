package blackjack.model;

import blackjack.model.participant.BettingAmount;
import blackjack.model.participant.Player;
import java.util.Map;

public class BettingPool {
    private static final double BLACKJACK_PROFIT_RATE = 1.5;

    private final Map<Player, BettingAmount> moneys;

    public BettingPool(final Map<Player, BettingAmount> moneys) {
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
}
