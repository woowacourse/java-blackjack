package blackjack.model;

import blackjack.model.player.Dealer;
import blackjack.model.player.Entry;
import blackjack.model.player.Player;
import java.util.HashMap;
import java.util.Map;

public final class Profits {
    private final Map<Player, Amount> values;

    private Profits(Map<Player, Amount> values) {
        this.values = Map.copyOf(values);
    }

    public static Profits of(Map<Entry, Amount> entryProfits, Dealer dealer) {
        Map<Player, Amount> map = new HashMap<>();
        map.put(dealer, getDealerProfit(entryProfits));
        for (Entry entry : entryProfits.keySet()) {
            map.put(entry, entryProfits.get(entry));
        }
        return new Profits(map);
    }

    private static Amount getDealerProfit(Map<Entry, Amount> entryProfits) {
        Amount totalEntryProfits = null;
        for (Amount amount : entryProfits.values()) {
            totalEntryProfits = amount.add(totalEntryProfits);
        }
        return totalEntryProfits.reverse();
    }

    public Map<Player, Amount> getValues() {
        return values;
    }
}
