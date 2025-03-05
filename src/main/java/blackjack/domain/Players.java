package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final CardPack cardPack;
    private final List<Player> players;

    public Players(List<String> playerNames) {
        cardPack = new CardPack(new RandomBlackjackShuffle());
        players = playerNames.stream()
                .map(this::initPlayer)
                .collect(Collectors.toList());
    }

    private Player initPlayer(String nickname) {
        Player player = new Player(nickname);
        player.pushDealCard(cardPack, 2);

        return player;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
