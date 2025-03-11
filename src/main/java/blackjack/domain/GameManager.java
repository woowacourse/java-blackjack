package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;

import java.util.List;

public class GameManager {

    private final CardPack cardPack;
    private final Players players;

    public GameManager(List<Gambler> gamblers) {
        cardPack = new CardPack();
        players = new Players(gamblers);
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
