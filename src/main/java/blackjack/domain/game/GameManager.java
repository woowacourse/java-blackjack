package blackjack.domain.game;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class GameManager {

    private final CardPack cardPack;
    private final Players players;

    public GameManager(CardPack cardPack, Players players) {
        this.cardPack = cardPack;
        this.players = players;
    }

    public void dealInitCards() {
        players.dealInitCardsToPlayers(cardPack);
    }

    public Players getPlayers() {
        return players;
    }

    public void dealAddCard(Player player) {
        player.pushDealCards(cardPack.getDealCards(1));
    }

    public boolean isDealerHitThenDealAddCard(Dealer dealer) {
        if (dealer.isDealerHit()) {
            dealer.pushDealCards(cardPack.getDealCards(1));
            return true;
        }
        return false;
    }
}
