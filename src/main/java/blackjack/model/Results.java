package blackjack.model;

import blackjack.model.player.Entry;
import java.util.Map;

public final class Results {
    private final Map<Entry, Result> values;

    public Results(Map<Entry, Result> values) {
        this.values = Map.copyOf(values);
    }

    public Map<Entry, Result> getValues() {
        return values;
    }
}
