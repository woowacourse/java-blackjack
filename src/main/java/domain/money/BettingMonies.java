package domain.money;

import java.util.List;

public class BettingMonies {
    private final List<BettingMoney> bettingMonies;

    private BettingMonies(final List<BettingMoney> bettingMonies) {
        this.bettingMonies = bettingMonies;
    }

    public static BettingMonies of(final List<BettingMoney> bettingMonies) {
        return new BettingMonies(bettingMonies);
    }

    public int size() {
        return bettingMonies.size();
    }

    public Money get(int index) {
        return bettingMonies.get(index);
    }
}
