package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = List.copyOf(players);
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public Players receiveCards(Deck deck) {
        List<Player> newPlayers = new ArrayList<>();
        for (Player player : players) {
            Player updatedPlayer = player.receiveCards(deck.drawSecondTimes());
            newPlayers.add(updatedPlayer);
        }
        return new Players(newPlayers);
    }

    public int count() {
        return this.players.size();
    }

    public boolean canHit(int playerIndex) {
        return this.players.get(playerIndex).canHit();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Players hitPlayer(int index, TrumpCard card) {
        List<Player> newPlayers = new ArrayList<>(players);
        Player targetPlayer = players.get(index);
        Player updatedPlayer = targetPlayer.receiveCard(card);
        newPlayers.set(index, updatedPlayer);
        return new Players(newPlayers);
    }
}
