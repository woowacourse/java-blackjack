package domain.participant;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public void add(Player player) {
        players.add(player);
    }

    public void receiveCard(Card card) {
        players.forEach(player -> player.receiveCard(card));
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
