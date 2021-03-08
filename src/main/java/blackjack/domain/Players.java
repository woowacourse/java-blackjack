package blackjack.domain;

import blackjack.utils.CardDeck;

import java.util.*;

public class Players {
    private final List<Player> players;

    public Players(String namesInput, CardDeck cardDeck) {
        players = new ArrayList<>();
        validate(namesInput);
        String[] names = namesInput.split(",");
        validateDuplicate(names);
        for (String name : names) {
            players.add(new Player(name, cardDeck));
        }
    }

    private void validateDuplicate(String[] names) {
        Set<String> namesCopy = new HashSet<>(Arrays.asList(names));
        if (namesCopy.size() != names.length) {
            throw new IllegalArgumentException("hello");
        }
    }

    private void validate(String namesInput) {
        if (namesInput == null || namesInput.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public List<Player> values() {
        return Collections.unmodifiableList(players);
    }
}
