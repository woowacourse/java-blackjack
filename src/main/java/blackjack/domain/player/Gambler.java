package blackjack.domain.player;

import blackjack.domain.BettingMoney;
import blackjack.domain.Name;
import blackjack.domain.card.GamblerCards;
import blackjack.util.NullChecker;

public final class Gambler extends Player implements Drawable {

    private static final int BASES_SCORE_CAN_DRAW = 21;

    private final BettingMoney bettingMoney;

    public Gambler(Name name, BettingMoney bettingMoney) {
        this(name, bettingMoney, new GamblerCards());
    }

    public Gambler(Name name, BettingMoney bettingMoney, GamblerCards gamblerCards) {
        super(name, gamblerCards);
        NullChecker.validateNotNull(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    @Override
    public boolean canDraw() {
        return isEqualOrUnderScore(BASES_SCORE_CAN_DRAW);
    }

    public Integer getBettingMoneyMultiply(double profitRatio) {
        return bettingMoney.multiply(profitRatio);
    }
}
