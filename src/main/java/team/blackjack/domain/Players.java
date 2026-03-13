package team.blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayerList() {
        return List.copyOf(players);
    }

    public void initPlayerHands(Deck deck) {
        for (Player player : players) {
            player.hit(deck.draw());
            player.hit(deck.draw());
        }
    }

    public Map<String, List<Card>> getCardsByPlayer(){
        final HashMap<String, List<Card>> result = new HashMap<>();
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

    public Map<String, Integer> getPlayerScoresByPlayer() {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        Player::getScore)
                );
    }

    public Player getPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 플레이어가 존재하지 않습니다."));
    }
}
