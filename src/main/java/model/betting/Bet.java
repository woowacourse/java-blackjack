package model.betting;

import java.util.Objects;
import model.participant.Dealer;
import model.participant.Player;

public class Bet {
    private final int money;
    private final Player better;
    private final Object owner; //TODO 인터페이스

    public Bet(int money, Player better) {
        this(money, better, better);
    }

    public Bet(int money, Player better, Object owner) {
        this.money = money;
        this.better = better;
        this.owner = owner;
    }

    public Bet increase(double rate) {
        int increaseAmount = (int) (money * rate);
        return new Bet(increaseAmount, better);
    }

    public Bet changeOwnerTo(Dealer dealer) {
        return new Bet(money, better, dealer);
    }

    public int calculateBetterRevenue() {
        if (isEqualOwnerAndBetter()) {
            return money;
        }
        return -money;
    }

    public int calculateDealerRevenue() {
        return -calculateBetterRevenue();
    }

    public boolean ownerEquals(Dealer dealer) {
        return this.owner.equals(dealer);
    }

    public boolean betterEquals(Player player) {
        return better.equals(player);
    }

    private boolean isEqualOwnerAndBetter() {
        return better.equals(owner);
    }

    public Object getOwner() {
        return owner;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bet bet = (Bet) o;
        return money == bet.money && Objects.equals(owner, bet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, owner);
    }
}
