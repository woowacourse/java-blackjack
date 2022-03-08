package blackjack.domain;

import java.util.List;

public class PlayerGroup {
    private final List<Player> players;

    public PlayerGroup(List<Player> players) {
        this.players = players;
    }

    public void addTwoCards(Card firstCard, Card secondCard) {
        for (Player player : players) {
            player.addTwoCards(firstCard, secondCard);
        }
    }
}
