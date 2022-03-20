package domain.participant;

import domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> names, List<Integer> moneys) {
        List<Player> tmpPlayers = new ArrayList<>();
        for (int idx = 0; idx < names.size(); idx++) {
            tmpPlayers.add(new Player(names.get(idx), moneys.get(idx)));
        }
        return new Players(tmpPlayers);
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

    public Map<Player, Integer> checkResults(Dealer dealer) {
        return players.stream()
            .collect(Collectors.toMap(
                Function.identity(),
                player-> player.multiply(PlayerResult.of(player,dealer)),
                (player,result)-> result,
                LinkedHashMap::new
            ));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
