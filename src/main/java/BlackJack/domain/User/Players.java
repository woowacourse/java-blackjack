package BlackJack.domain.User;
import java.util.ArrayList;
import java.util.List;

import static BlackJack.domain.Card.CardFactory.CARD_CACHE;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> playersNames) {
        for (String name : playersNames) {
            this.players.add(new Player(name));
        }
    }

    public void recieveCard() {
        for (Player player : players) {
            player.addCard(CARD_CACHE.poll());
        }
    }

    public void checkPlayersBlackJack() {
        for (Player player : players) {
            player.checkBlackJack();
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
