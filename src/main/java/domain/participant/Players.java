package domain.participant;

import domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public static Players of(List<String> playerNames) {
        List<Player> players = playerNames.stream()
            .map(String::trim)
            .map(Player::new)
            .collect(Collectors.toList());

        return new Players(players);
    }

    public void runInitialTurn(Deck deck) {
        for (Player player : players) {
            player.pickTwoCards(deck);
        }
    }

    public List<String> toNames() {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    public List<Result> checkResults(Dealer dealer) {
        return players.stream()
            .map(player -> player.judgeResult(dealer))
            .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
