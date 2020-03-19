package blackjack.domain.gamer;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {

    private List<Player> players;

    public Players(List<Player> players) {
        if (Objects.isNull(players) || players.isEmpty()) {
            throw new IllegalArgumentException("플레이어가 없습니다.");
        }
        this.players = players;
    }

    public static Players from(List<String> names) {
        if (Objects.isNull(names) || names.isEmpty()) {
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

    public Player findPlayerBy(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 유저가 없습니다."));
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
