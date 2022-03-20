package domain.participant;

import domain.card.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public static Players of(Map<String, String> bettingMoneys) {
        List<Player> players = new ArrayList<>();
        for (String playerName : bettingMoneys.keySet()) {
            players.add(Player.of(playerName, bettingMoneys.get(playerName)));
        }
        return new Players(players);
    }

    public void runInitialTurn(Deck deck) {
        for (Player player : players) {
            player.receiveInitialTwoCards(deck);
        }
    }

    public List<String> toNames() {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    public List<Integer> calculateIncomes(Dealer dealer) {
        return players.stream()
            .map(player -> player.calculateIncome(player.receiveResult(dealer)))
            .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
