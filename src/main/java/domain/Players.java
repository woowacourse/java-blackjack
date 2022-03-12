package domain;

import domain.card.CardDeck;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public final class Players {
    private final List<Player> players;

    public Players(List<String> names) {
        this.players = names
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void receiveCard() {
        for (Player player : players) {
            player.receiveCard(CardDeck.draw());
        }
    }
}
