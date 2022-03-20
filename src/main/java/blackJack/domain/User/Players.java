package blackJack.domain.User;

import blackJack.domain.Card.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> playersNames, Map<String, Integer> bettingMoneys) {
        for (String playersName : playersNames) {
            players.add(new Player(playersName, bettingMoneys.get(playersName)));
        }
    }

    public void dealCardToPlayers(Deck deck) {
        for (Player player : players) {
            player.dealCard(deck.getCard());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }
}
