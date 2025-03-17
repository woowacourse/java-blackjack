package blackjack.model.player;

import blackjack.model.BettingMoney;
import blackjack.model.card.Cards;

public class User extends Player {

    private final BettingMoney bettingMoney;

    public User(final String name, final int bettingAmount) {
        super(name);
        this.bettingMoney = new BettingMoney(bettingAmount);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public Cards openCards() {
        return new Cards(cards.getValues());
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

}
