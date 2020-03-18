package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public void receiveInitialCards(CardDeck cardDeck) {
        players.forEach(player -> player.receiveInitialCards(cardDeck));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getNames() {
        return this.players
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }
}
