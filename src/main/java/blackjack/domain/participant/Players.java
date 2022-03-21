package blackjack.domain.participant;

import blackjack.domain.Betting;
import blackjack.domain.card.CardDeck;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Players implements Iterable<Player> {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Players(PlayerNames names, CardDeck deck, Function<Name, Betting> inputBetting) {
        this(createPlayers(names, deck, inputBetting));
    }

    public static List<Player> createPlayers(PlayerNames names, CardDeck deck,
                                             Function<Name, Betting> inputBetting) {
        return names.getNames()
                .stream()
                .map(name -> new Player(name, deck.drawDouble(), inputBetting.apply(name)))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Iterator<Player> iterator() {
        return new Iterator<>() {
            int current = 0;

            @Override
            public boolean hasNext() {
                return current < players.size();
            }

            @Override
            public Player next() {
                return players.get(current++);
            }
        };
    }

    public Stream<Player> stream() {
        return players.stream();
    }
}
