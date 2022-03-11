package blackjack;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Entry> entries;
    private final Dealer dealer;


    private Players(List<Entry> entries, Dealer dealer) {
        this.entries = entries;
        this.dealer = dealer;
    }

    public static Players from(List<String> names) {
        return new Players(entriesFrom(names), new Dealer());
    }

    private static List<Entry> entriesFrom(List<String> names) {
        return names.stream()
                .map(Entry::new)
                .collect(Collectors.toList());
    }
}
