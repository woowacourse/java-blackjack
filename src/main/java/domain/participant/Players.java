package domain.participant;

import domain.card.CardDeck;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Players {
    private final List<Player> players;

    public Players(final List<String> names) {
        this.players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void drawCard() {
        for (final Player player : players) {
            player.drawCard(CardDeck.draw());
        }
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
