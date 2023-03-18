package blackjack.domain.user;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public int getTotalBetAmount() {
        return players.stream()
                .map(Player::getBetAmount)
                .reduce(Integer::sum)
                .orElseThrow(() -> new NoSuchElementException("현재 플레이어가 없습니다."));
    }
}
