package blackjack;

import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final List<Player> players;
    private final TrumpCardPack trumpCardPack;

    private Game(List<String> names) {
        this.trumpCardPack = new TrumpCardPack();
        this.players = buildPlayers(names);
    }

    private List<Player> buildPlayers(List<String> names) {
        List<Player> players = buildEntries(names);
        players.add(buildDealer());
        return players;
    }

    private List<Player> buildEntries(List<String> names) {
        return names.stream()
                .map(this::buildEntry)
                .collect(Collectors.toList());
    }

    private Player buildEntry(String name) {
        return new Entry.Builder(name)
                .deck(trumpCardPack.draw(), trumpCardPack.draw())
                .build();
    }

    private Player buildDealer() {
        return new Dealer.Builder()
                .deck(trumpCardPack.draw(), trumpCardPack.draw())
                .build();
    }
}
