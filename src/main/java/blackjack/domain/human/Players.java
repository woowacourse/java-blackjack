package blackjack.domain.human;

import blackjack.domain.card.CardDeck;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    public static final String JOIN_DELIMITER = ", ";

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public int size() {
        return players.size();
    }

    public void giveCard(CardDeck cardDeck) {
        for (Player player : players) {
            player.addCard(cardDeck.giveCard());
        }
    }

    public List<Player> getCardNeedPlayers() {
        return players.stream()
            .filter(Player::isOneMoreCard)
            .collect(Collectors.toList());
    }

    public String getPlayerNames() {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.joining(JOIN_DELIMITER));
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
