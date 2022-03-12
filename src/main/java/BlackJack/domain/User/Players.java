package BlackJack.domain.User;

import BlackJack.domain.Card.CardFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public static Players join(List<String> playerNames) {
        return playerNames.stream()
                .map(name -> new Player(name, CardFactory.initCards()))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Players::new
                ));
    }

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
