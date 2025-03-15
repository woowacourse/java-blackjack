package model.betting;

import java.util.Objects;
import model.participant.Dealer;
import model.participant.Player;

public class Bet {
    private final int money;
    private final Bettable better;
    private final BetOwnable owner;

    public Bet(int money, Bettable better) {
        this(money, better, better);
    }

    public Bet(int money, Bettable better, BetOwnable owner) {
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

    public boolean betterEquals(Player player) {
        return better.equals(player);
    }

    private boolean isEqualOwnerAndBetter() {
        return better.equals(owner);
    }

    public Object getOwner() {
        return owner;
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
