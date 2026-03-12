package blackjack.domain;

import java.util.List;
import java.util.Objects;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        validate(players);
        this.players = List.copyOf(players);
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    private void validate(List<Player> players) {
        Objects.requireNonNull(players, "players 은 null 이 올 수 없습니다.");
    }

    public void receiveCards(Deck deck) {
        for (Player player : players) {
            player.receiveCards(deck.drawInitialCards());
        }
    }

    public int count() {
        return this.players.size();
    }

    public Player playerAt(int index) {
        return players.get(index);
    }

    public List<Player> getPlayers() {
        return players;
    }
}