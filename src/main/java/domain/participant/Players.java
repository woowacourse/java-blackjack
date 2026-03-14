package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public void add(Player player) {
        players.add(player);
    }

    public void receiveCard(Deck deck) {
        players.forEach(player -> player.receiveCard(deck.draw()));
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public List<Player> getNonNaturalBlackJackPlayers() {
        return players.stream()
                .filter(player -> !player.isNaturalBlackJack())
                .toList();
    }

    public void updateNaturalBlackJackStatus() {
        players.stream()
                .filter(Player::isBlackJack)
                .forEach(Player::markNaturalBlackJack);
    }
}
