package blackjack.model.bet;

import blackjack.model.player.Dealer;
import blackjack.model.player.Entry;
import blackjack.model.player.Player;
import java.util.HashMap;
import java.util.Map;

public final class Profits {
    private final Map<Player, Profit> values;

    private Profits(Map<Player, Profit> values) {
        this.values = Map.copyOf(values);
    }

    public static Profits of(Map<Entry, Profit> entryProfits, Dealer dealer) {
        Map<Player, Profit> map = new HashMap<>();
        map.put(dealer, getDealerProfit(entryProfits));
        for (Entry entry : entryProfits.keySet()) {
            map.put(entry, entryProfits.get(entry));
        }
        return new Profits(map);
    }

    private static Profit getDealerProfit(Map<Entry, Profit> entryProfits) {
        Profit totalEntryProfits = null;
        for (Profit profit : entryProfits.values()) {
            totalEntryProfits = profit.add(totalEntryProfits);
        }
        return totalEntryProfits.reverse();
    }

    public Map<Player, Profit> getValues() {
        return values;
    }
}
