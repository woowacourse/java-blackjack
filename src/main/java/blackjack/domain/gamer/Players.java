package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Players {
    private final List<Player> players;

    public Players(String players) {
        List<Player> list = splitPlayer(players);
        validate(list);
        this.players = new ArrayList<>(list);
    }

    private void validate(List<Player> list) {
        list.stream()
                .filter(player -> player.isEqualName(""))
                .findAny()
                .ifPresent(name -> {throw new IllegalArgumentException("유효하지 않은 플레이어 이름입니다.");});
    }

    private List<Player> splitPlayer(String players) {
        return Arrays.stream(players.split(","))
                .map(s -> s.replaceAll(" ", ""))
                .map(Player::new)
                .collect(toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
