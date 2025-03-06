package blackjack.domain;

import java.util.List;

public class GameManager {

    private final CardPack cardPack;
    private final Players players;

    public GameManager() {
        cardPack = new CardPack(new RandomBlackjackShuffle());
        players = new Players();
    }

    public void addGamblers(List<String> gamblerNames) {
        List<Player> gamblers = gamblerNames.stream()
                .map(Player::new)
                .toList();
        players.addGamblers(gamblers);
    }

    public Players getPlayers() {
        return players;
    }
}
