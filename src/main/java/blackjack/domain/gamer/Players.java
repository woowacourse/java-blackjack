package blackjack.domain.gamer;

import java.util.List;
import java.util.Optional;

import blackjack.domain.deck.Deck;

public class Players {

    private final List<Player> players;
    private int cursor = 0;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public void initialize(Deck deck) {
        for (var player : players) {
            player.initialize(deck);
        }
    }

    public boolean hasNext() {
        return cursor < players.size();
    }

    public Player next() {
        if (!hasNext()) {
            throw new IllegalStateException("[ERROR] 플레이어 수를 넘어선 접근입니다.");
        }
        return players.get(cursor++);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Optional<Player> findByName(String name) {
        return players.stream()
            .filter(player -> player.getName().equals(name))
            .findAny();
    }

    public Player getByName(String name) {
        return findByName(name)
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 플레이어명입니다."));
    }
}
