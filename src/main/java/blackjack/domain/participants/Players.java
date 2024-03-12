package blackjack.domain.participants;


import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public void distributeHands(List<Hands> allHands) {
        for (int index = 0; index < players.size(); index++) {
            Hands hands = allHands.get(index);
            Player player = players.get(index);
            player.receiveHands(hands);
        }
    }

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
