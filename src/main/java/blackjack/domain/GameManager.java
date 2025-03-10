package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class GameManager {

    private final CardPack cardPack;
    private final Players players;

    public GameManager(final CardPack cardPack, final Players players) {
        this.cardPack = cardPack;
        this.players = players;
    }

    public void dealAddCard(final Player player) {
        player.pushDealCard(cardPack, 1);
    }

    public boolean isPlayerBust(final Player player) {
        return player.isBust();
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
