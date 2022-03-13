package domain.participant;

import domain.card.Deck;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final String DELIMITER = ",";

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(String names) {
        List<Player> players = Arrays.stream(names.split(DELIMITER))
            .map(String::trim)
            .map(Player::new)
            .collect(Collectors.toList());

        return new Players(players);
    }

    public void runInitialTurn(Deck deck) {
        for (Player player : players) {
            player.hitInitialTurn(deck);
        }
    }

    public List<String> toNames() {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    public List<Result> checkResults(Dealer dealer) {
        return players.stream()
            .map(player -> player.isWin(dealer))
            .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
