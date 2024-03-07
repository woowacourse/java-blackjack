package blackjack.domain;

import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<String> players) {
        this.players = players.stream()
                .map(Player::new)
                .toList();
    }

    public void handOutInitialCards(Deck deck) {
        for (Player player : players) {
            player.addCard(deck.draw());
            player.addCard(deck.draw());
        }
    }
}
