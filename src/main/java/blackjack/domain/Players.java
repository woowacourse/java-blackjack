package blackjack.domain;

import java.util.List;
import java.util.Objects;

public class Players {

    private static final int MAX_PLAYER = 10;
    private static final String TOO_MANY_PLAYER_ERROR_MESSAGE = "최대 플레이어 수는 " + MAX_PLAYER + "명 입니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        validateSize(players);

        this.players = players;
    }

    private void validateSize(List<Player> players) {
        if (players.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(TOO_MANY_PLAYER_ERROR_MESSAGE);
        }
    }

    public void initCards(Deck deck) {
        for (Player player : players) {
            player.hit(deck.pick());
            player.hit(deck.pick());
        }
    }

    public List<Player> get() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Players)) {
            return false;
        }
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }

    @Override
    public String toString() {
        return "Players{" +
                "players=" + players +
                '}';
    }
}
