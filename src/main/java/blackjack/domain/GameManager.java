package blackjack.domain;

import java.util.List;

public class GameManager {

    private final CardPack cardPack;
    private final Players players;

    public GameManager() {
        cardPack = new CardPack(new RandomBlackjackShuffle());
        players = new Players();
    }

    public void addGamblers(List<Player> gamblerNames) {
        players.addGamblers(gamblerNames);
        players.initPlayers(cardPack);
    }

    public Players getPlayers() {
        return players;
    }

    public void dealAddCard(Player player) {
        players.dealAddCard(cardPack, player);
    }
}
