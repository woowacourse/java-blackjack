package blackjack.domain.user;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> names) {
        return new Players(names.stream()
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<Cards> showCardsByUsers() {
        return players.stream()
                .map(Player::cards)
                .collect(Collectors.toList());
    }

    public boolean isRemainToProceedPlayers() {
        return players.stream()
                .anyMatch(User::isAbleToHit);
    }

    public Player getCurrentPlayer() {
        return players.stream()
                .filter(User::isAbleToHit)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("남아있는 플레이어가 없습니다."));
    }
}
