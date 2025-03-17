package blackjack.model.player;

import java.math.BigDecimal;

import blackjack.model.card.Cards;

public class User extends Player {

    private static final int USER_DRAW_THRESHOLD = 21;
    private final BettingMoney bettingMoney;

    public User(final String name, final int bettingAmount) {
        super(name);
        this.bettingMoney = new BettingMoney(bettingAmount);
    }

    public BigDecimal calculateProfit(final double profitRatio) {
        return bettingMoney.multiply(profitRatio);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public Cards openCards() {
        return new Cards(cards.getValues());
    }

    @Override
    public boolean canDrawMoreCard() {
        return calculatePoint() < USER_DRAW_THRESHOLD;
    }

}
