package model.player;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;

public class Players {

    private static final String INVALID_PLAYERS_SIZE = "플레이어 수는 1명 이상이어야 합니다.";

    private final List<Player> players;

    private Players(List<Player> players) {
        validateEmptyPlayers(players);
        this.players = players;
    }

    public static Players from(List<String> playerNames) {
        return playerNames.stream()
            .map(Player::new)
            .collect(collectingAndThen(toList(), Players::new));
    }

    private void validateEmptyPlayers(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException(INVALID_PLAYERS_SIZE);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Players addCards(List<Card> cardsElement) {
        int index = 0;
        List<Player> updatedPlayers = new ArrayList<>();
        for (Player player : players) {
            Player updatedPlayer = player.addCards(cardsElement.subList(index, index + 2));
            updatedPlayers.add(updatedPlayer);
            index += 2;
        }
        return new Players(updatedPlayers);
    }
}
