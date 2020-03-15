package domain.gamer;

import domain.card.Deck;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players valueOf(Deck deck, List<String> playerNames) {
        validateDuplicateName(playerNames);
        return playerNames.stream()
                .map(name -> new Player(deck.dealInitCards(), name))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }

    private static void validateDuplicateName(List<String> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new DuplicatedNameException("중복되는 이름이 존재합니다.");
        }
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
