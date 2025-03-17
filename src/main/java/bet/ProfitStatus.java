package bet;

import participant.Player;

import java.util.Map;

public class ProfitStatus {

    private final Map<Player, Money> values;

    public ProfitStatus(Map<Player, Money> values) {
        this.values = values;
    }

    public Map<Player, Money> getValues() {
        return values;
    }
}
