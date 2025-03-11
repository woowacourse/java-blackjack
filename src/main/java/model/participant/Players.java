package model.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import model.deck.Deck;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        validateDuplicateNames(players);
        this.players = players;
    }

    public static Players createByNames(final List<String> names, final Deck deck) {
        return new Players(names.stream()
                .map(name -> new Player(name, deck))
                .collect(Collectors.toList()));
    }

    private void validateDuplicateNames(List<Player> players) {
        Set<String> uniqueNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.toSet());

        if (uniqueNames.size() != players.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복되지 않아야 합니다.");
        }
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
