package blackjack.model;

import blackjack.model.player.Dealer;
import blackjack.model.player.Entry;
import blackjack.model.player.Player;
import java.util.HashMap;
import java.util.Map;

public final class Profits {
    private final Map<Player, Money> values;

    private Profits(Map<Player, Money> values) {
        this.values = Map.copyOf(values);
    }

    public static Profits of(Map<Entry, Money> entryProfits, Dealer dealer) {
        Map<Player, Money> map = new HashMap<>();
        map.put(dealer, getDealerProfit(entryProfits));
        for (Entry entry : entryProfits.keySet()) {
            map.put(entry, entryProfits.get(entry));
        }
        return new Profits(map);
    }

    private static Money getDealerProfit(Map<Entry, Money> entryProfits) {
        Money totalEntryProfits = null;
        for (Money money : entryProfits.values()) {
            totalEntryProfits = money.add(totalEntryProfits);
        }
        return totalEntryProfits.reverse();
    }

    public Map<Player, Money> getValues() {
        return values;
    }
}
