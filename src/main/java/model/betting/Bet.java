package model.betting;

import java.util.Objects;
import model.participant.role.BetOwnable;
import model.participant.role.Bettable;

public final class Bet {
    private final Money money;
    private final Bettable better;
    private final BetOwnable owner;

    public Bet(final Money money, final Bettable better) {
        this(money, better, better);
    }

    public Bet(final Money money, final Bettable better, final BetOwnable owner) {
        this.money = money;
        this.better = better;
        this.owner = owner;
    }

    public Bet increase(final IncreasingRate increasingRate) {
        Money increasedMoney = new Money(increasingRate.multiplyWith(money));
        return new Bet(increasedMoney, better);
    }

    public Bet changeOwnerTo(final BetOwnable owner) {
        return new Bet(money, better, owner);
    }

    public int calculateBetterRevenue() {
        if (isEqualOwnerAndBetter()) {
            return money.getValue();
        }
        return money.getReverseValue();
    }

    public int calculateOwnerRevenue() {
        return money.getValue();
    }

    public boolean betterEquals(final Bettable better) {
        return this.better.equals(better);
    }

    private boolean isEqualOwnerAndBetter() {
        return better.equals(owner);
    }

    public BetOwnable getOwner() {
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
        return Objects.equals(money, bet.money) && Objects.equals(better, bet.better)
                && Objects.equals(owner, bet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, better, owner);
    }
}
