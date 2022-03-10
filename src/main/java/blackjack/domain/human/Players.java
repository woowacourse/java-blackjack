package blackjack.domain.human;

import blackjack.domain.card.CardDeck;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
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

    public void giveCard() {
        for (Player player : players) {
            player.addCard(CardDeck.giveCard());
        }
    }

    public List<Player> getPlayers(){
        return List.copyOf(players);
    }

    public String getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }
}
