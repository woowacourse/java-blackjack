package domain.money;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Monies {
    private final List<Money> bettingMonies;

    private Monies(final List<Money> bettingMonies) {
        this.bettingMonies = bettingMonies;
    }

    public static Monies of(final List<Integer> bettingMoneyValues) {
        List<Money> bettingMonies = bettingMoneyValues.stream()
                .map(bettingMoneyValue -> new Money(bettingMoneyValue))
                .collect(Collectors.toList());
        return new Monies(bettingMonies);
    }

    public int size() {
        return bettingMonies.size();
    }

    public Money get(int index) {
        return bettingMonies.get(index);
    }

    public List<Money> getBettingMonies() {
        return Collections.unmodifiableList(bettingMonies);
    }
}
