package blackjack.domain.human;

import blackjack.domain.card.CardDeck;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    public static final String PLAYER_NAME_DELIMITER = ", ";
    private final List<Player> players;
    
    private Players(final List<Player> players) {
        this.players = players;
    }
    
    public static Players of(final List<Player> players) {
        return new Players(players);
    }
    
    public int size() {
        return players.size();
    }
    
    public void giveCard(CardDeck cardDeck) {
        for (Player player : players) {
            player.addCard(cardDeck.draw());
        }
    }
    
    public List<Player> get() {
        return List.copyOf(players);
    }
    
    public String getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(PLAYER_NAME_DELIMITER));
    }
}
