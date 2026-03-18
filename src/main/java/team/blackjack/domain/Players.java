package team.blackjack.domain;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    private final Set<Player> players;

    public Players(Set<String> playerNames) {
        this.players =  playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Players() {
        this.players = new LinkedHashSet<>();
    }

    public Set<Player> getPlayers() {
        return new LinkedHashSet<>(players);
    }

    public Map<String, Integer> getScoresByPlayer() {
        final HashMap<String, Integer> result = new HashMap<>();
        for (Player player : players) {
            result.put(player.getName(), player.getScore());
        }

        return result;
    }

    public void initPlayerHands(Deck deck) {
        for (Player player : players) {
            player.hit(deck.draw());
            player.hit(deck.draw());
        }
    }

    public Map<String, Set<Card>> getCardsByPlayer(){
        final HashMap<String, Set<Card>> result = new HashMap<>();
        for (Player player : players) {
            result.put(player.getName(), player.getCards());
        }

        return result;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public Player getPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 플레이어가 존재하지 않습니다."));
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
    }
}
