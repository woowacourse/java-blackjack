package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public Map<String, List<Card>> getCardsWithName() {
        final Map<String, List<Card>> cardsWithNameTotal = new LinkedHashMap<>();
        for (final Player player : players) {
            cardsWithNameTotal.putAll(player.getNameWithCards());
        }
        return cardsWithNameTotal;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
