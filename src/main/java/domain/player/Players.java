package domain.player;

import domain.deck.Deck;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Players {

    private final List<Player> players;

    public Players(final List<String> names) {
        this.players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void hitTwice(final Deck deck) {
        players.forEach(player -> {
            player.hit(deck.popCard());
            player.hit(deck.popCard());
        });
    }

    public void initBet(final int money, final String name) {
        findPlayer(name).initBet(money);
    }

    public Player findPlayer(final String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR]: 해당 이름을 가진 플레이어가 존재하지 않습니다."));
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
