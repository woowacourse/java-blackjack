package blackjack.domain;

import blackjack.domain.card.BlackjackShuffle;
import blackjack.domain.card.CardPack;
import blackjack.domain.player.Player;

import java.util.List;

public class GameManager {

    private final CardPack cardPack;
    private final Players players;

    public GameManager(final BlackjackShuffle blackjackShuffle) {
        cardPack = new CardPack(blackjackShuffle);
        players = new Players();
    }

    public void addGamblers(List<Player> gamblerNames) {
        players.addGamblers(gamblerNames);
        players.initPlayers(cardPack);
    }

    public void dealAddCard(final Player player) {
        player.pushDealCard(cardPack, 1);
    }

    public boolean isPlayerBust(final Player player) {
        return players.isPlayerBust(player);
    }

    public boolean isDealerHitThenDealAddCard() {
        if (players.isDealerHit()) {
            players.dealAddCardForDealer(cardPack);
            return true;
        }
        return false;
    }

    public Players getPlayers() {
        return players;
    }
}
