package blackjack.model;

import blackjack.model.player.Entry;
import java.util.Map;

public final class Results {
    private final Map<Entry, Money> values;

    public Results(Map<Entry, Money> values) {
        this.values = Map.copyOf(values);
    }

    public Map<Entry, Money> getValues() {
        return values;
    }
}
