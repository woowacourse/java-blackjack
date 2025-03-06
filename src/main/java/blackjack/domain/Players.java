package blackjack.domain;

import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Nickname> nicknames) {
        this.players = nicknames.stream()
                .map(Player::new)
                .toList();
    }

    public List<Player> getPlayers() {
        return players.stream().filter(player -> !player.isDealer()).toList();
    }

    public Player getDealer() {
        return players.stream().filter(Player::isDealer)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("딜러가 등록되어 있어야 합니다."));
    }
}
