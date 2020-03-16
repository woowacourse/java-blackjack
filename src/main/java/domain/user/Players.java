package domain.user;

import domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players = new ArrayList<>();
    private static final String NULL_OR_EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있습니다.";

    public Players(List<String> playerNames) {
        if (playerNames == null || playerNames.isEmpty()) {
            throw new NullPointerException(NULL_OR_EMPTY_NAME_ERROR_MESSAGE);
        }
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
    }

    public void receiveFirstCards(Deck deck) {
        for (Player player : players) {
            player.receiveFirstCards(deck);
        }
    }

    public List<String> getNames() {
        return players.stream().
            map(Player::getName)
            .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

}
