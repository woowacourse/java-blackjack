package participant;

import card.Deck;
import card.Hand;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

//    public static Players createByNames(List<String> playerNames) {
//        List<Player> players = playerNames.stream()
//                .map(playerName -> new Player(playerName, Hand.createEmpty()))
//                .toList();
//        return new Players(players);
//    }

    public static Players create(List<Player> players) {
        return new Players(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Map<String, Hand> openNameAndInitialHand() {
        return players.stream()
                .collect(Collectors.toMap(Player::getName,
                        Player::openInitialHand));
    }

    public void initializeHand(Deck deck) {
        for (Player player : players) {
            player.initializeHand(deck.drawInitialHand());
        }
    }
}
