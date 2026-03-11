package domain.pariticipant;

import domain.card.CardShuffler;
import domain.card.Deck;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public void drawInitialCards(Deck deck, CardShuffler cardShuffler) {
        for (Player player : players) {
            player.drawInitialCards(deck, cardShuffler);
        }
    }
}
