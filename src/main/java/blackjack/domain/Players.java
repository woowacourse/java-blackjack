package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        validate(players);
        this.players = List.copyOf(players);
    }

    private void validate(List<Player> players) {
        Objects.requireNonNull(players, "플레이어 목록은 null일 수 없습니다.");
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 1명 이상이어야 합니다.");
        }
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

   public void forEach(Consumer<Player> action){
        players.forEach(action);
   }

    public int count() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
