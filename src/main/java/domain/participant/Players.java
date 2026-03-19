package domain.participant;

import domain.card.Deck;
import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public void dealOneCardToPlayers(Deck deck) {
        players.forEach(player -> player.addCard(deck.draw()));
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public List<Player> getPlayersInTurn() {
        return players.stream()
                .filter(player -> !player.isNaturalBlackJack())
                .toList();
    }
}
