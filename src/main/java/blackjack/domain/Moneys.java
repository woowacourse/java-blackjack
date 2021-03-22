package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class Moneys {

    private final List<Money> moneys;

    public Moneys(List<Money> moneys) {
        this.moneys = moneys;
    }

    public Money get(int index) {
        return moneys.get(index);
    }

    public List<Money> toList() {
        return Collections.unmodifiableList(moneys);
    }
}
