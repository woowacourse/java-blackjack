package player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import card.Card;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public void add(Player player) {
        players.add(player);
    }

    public void takeCard(int playerIndex, Card card) {
        Player player = players.get(playerIndex);
        player.hit(card);
    }

    public int count() {
        return players.size();
    }

    public boolean isBust(int playerIndex) {
        return players.get(playerIndex).isBust();
    }

    public List<PlayerResultDto> getPlayerResults() {
        return players.stream()
                .map(PlayerResultDto::from)
                .collect(Collectors.toUnmodifiableList());
    }
}
