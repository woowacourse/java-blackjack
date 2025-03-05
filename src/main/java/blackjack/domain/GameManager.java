package blackjack.domain;

import java.util.List;

public class GameManager {

    private final CardPack cardPack;
    private final Players players;

    public GameManager(List<String> gamblerNames) {
        cardPack = new CardPack(new RandomBlackjackShuffle());
        getPlayersByNames(gamblerNames);
        players = new Players(getPlayersByNames(gamblerNames));
    }

    private List<Player> getPlayersByNames(List<String> gamblerNames) {
        return gamblerNames.stream()
                .map(Player::new)
                .toList();
    }

}
