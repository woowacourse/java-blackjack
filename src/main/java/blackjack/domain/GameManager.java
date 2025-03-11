package blackjack.domain;

import blackjack.domain.card.BlackjackShuffle;
import blackjack.domain.card.CardPack;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;

import java.util.List;

public class GameManager {

    private final CardPack cardPack;
    private final Players players;

    public GameManager(final BlackjackShuffle blackjackShuffle) {
        cardPack = new CardPack(blackjackShuffle);
        players = new Players();
    }

    public void addGamblersAndDealInitCards(List<Gambler> gamblers) {
        players.addGamblers(gamblers);
        players.dealInitCardsToPlayers(cardPack);
    }

    public Players getPlayers() {
        return players;
    }

    public void dealAddCard(Player player) {
        player.pushDealCard(cardPack, 1);
    }

    public boolean isDealerHitThenDealAddCard(Dealer dealer) {
        if (dealer.isDealerHit()) {
            dealer.pushDealCard(cardPack, 1);
            return true;
        }
        return false;
    }
}
