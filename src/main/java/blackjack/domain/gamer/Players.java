package blackjack.domain.gamer;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {

    private List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> names) {
        if (Objects.isNull(names)) {
            throw new IllegalArgumentException("잘못된 이름 형식입니다.");
        }
        List<Player> players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Players(players);
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
