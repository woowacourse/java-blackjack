package domain.user;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.deck.Deck;

public class Players {
    private List<Player> players;

    private Players(String input) {
        List<String> names = Arrays.asList(input.split(",", -1));
        this.players = names.stream()
                .map(String::trim)
                .map(Player::new)
                .collect(Collectors.toList());

    }

    public static Players of(String names) {
        return new Players(names);
    }

    public void draw(Deck deck) {
        players.forEach(
                player -> player.draw(deck.dealOut())
        );
    }

    public String getAllFirstDrawResult() {
        return players.stream()
                .map(Player::getFirstDrawResult)
                .collect(Collectors.joining("\n"));
    }

    public String getAllNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public List<Player> getPlayers() {
        return players;
    }
}
