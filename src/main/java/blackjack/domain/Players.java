package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final CardPack cardPack;
    private final Player dealer;
    private final List<Player> gamblers;

    public Players(List<String> playerNames) {
        cardPack = new CardPack(new RandomBlackjackShuffle());
        dealer = new Dealer();
        dealer.pushDealCard(cardPack, 2);
        gamblers = playerNames.stream()
                .map(this::initPlayer)
                .collect(Collectors.toList());
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getGamblers() {
        return gamblers;
    }

    private Player initPlayer(String nickname) {
        Player player = new Gambler(nickname);
        player.pushDealCard(cardPack, 2);

        return player;
    }
}
