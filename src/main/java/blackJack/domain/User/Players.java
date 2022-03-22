package blackJack.domain.User;

import blackJack.domain.Card.Deck;

import java.util.List;
import java.util.Objects;

public class Players {
    private List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
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
