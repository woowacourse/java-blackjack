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

    public Players(List<String> nameStrings, CardDeck deck, Function<String, Betting> inputBetting) {
        this(createPlayers(nameStrings, deck, inputBetting));
    }

    public static List<Player> createPlayers(List<String> nameStrings, CardDeck deck,
                                             Function<String, Betting> inputBetting) {
        validateNames(nameStrings);
        return nameStrings.stream()
                .map(name -> new Player(new Name(name), deck.drawDouble(), inputBetting.apply(name)))
                .collect(Collectors.toUnmodifiableList());
    }

    private static void validateNames(List<String> names) {
        validateSize(names);
        validateDistinct(names);
    }

    private static void validateSize(List<String> names) {
        if (names.size() < 2 || names.size() > 8) {
            throw new IllegalArgumentException("[ERROR] 2~8인의 플레이어가 참가할 수 있습니다.");
        }
    }

    private static void validateDistinct(List<String> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
        }
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
