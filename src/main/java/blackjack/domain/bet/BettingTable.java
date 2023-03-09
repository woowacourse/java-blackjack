package blackjack.domain.bet;

import java.util.HashMap;
import java.util.Map;

public class BettingTable {

    private final Map<String, Money> table;

    public BettingTable() {
        table = new HashMap<>();
    }

    public void bet(final String name, final Money money) {
        table.put(name, money);
    }

    public Money get(final String name) {
        return table.get(name);
    }
}
