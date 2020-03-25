package blackjack.domain.gamer;

import blackjack.controller.dto.PlayersBettingMoneyDto;

import java.util.*;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = Collections.unmodifiableList(players);
    }

    public static Players of(PlayersBettingMoneyDto playersBettingMoneyDto) {
        Map<String, String> playersBettingMoney = playersBettingMoneyDto.get();
        List<Player> players = new ArrayList<>();
        for (Map.Entry<String, String> entry : playersBettingMoney.entrySet()) {
            players.add(new Player(entry.getKey(), entry.getValue()));
        }
        return new Players(players);
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public Player findPlayer(String name) {
        return players.stream()
                .filter(value -> value.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(name + "플레이어가 존재하지 않습니다."));
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
