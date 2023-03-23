package domain.user;

import domain.game.Winning;
import domain.money.BettingAmount;

public class Player extends User {
    
    private final PlayerName name;
    private BettingAmount bettingAmount;

    public Player(final String name) {
        this.name = new PlayerName(name);
    }

    public void betting(final int bettingAmount) {
        this.bettingAmount = BettingAmount.valueOf(bettingAmount);
    }

    public Winning match(Dealer dealer) {
        return state.match(dealer.getState());
    }

    public boolean isRightName(final String name) {
        return new PlayerName(name).equals(this.name);
    }

    public BettingAmount getBettingAmount() {
        return bettingAmount;
    }

    @Override
    public boolean isHittable() {
        return state.isHittable();
    }

    public String getName() {
        return name.getName();
    }
}
