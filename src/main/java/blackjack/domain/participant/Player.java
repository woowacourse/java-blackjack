package blackjack.domain.participant;

import blackjack.domain.betting.BettingAmount;

import java.math.BigDecimal;

public class Player extends Participant {
    private static final int MAX_CARD_SUM = 21;

    private final Name name;
    private final BettingAmount bettingAmount;

    public Player(String name, BettingAmount bettingAmount) {
        this.name = new Name(name);
        this.bettingAmount = bettingAmount;
    }

    public String getName() {
        return name.getName();
    }

    public BigDecimal calculateProfit(String ratio) {
        return bettingAmount.calculateProfit(ratio);
    }

    @Override
    public boolean canReceive() {
        return getScore().isLess(MAX_CARD_SUM);
    }
}
