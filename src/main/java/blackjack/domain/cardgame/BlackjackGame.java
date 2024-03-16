package blackjack.domain.cardgame;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.List;

public class BlackjackGame {
    private final CardDeck cardDeck;

    public BlackjackGame() {
        this.cardDeck = new CardDeck();
    }

    public void giveCard(final Player player) {
        player.addCard(cardDeck.draw());
    }

    public void initializeHand(final Dealer dealer, final List<Player> players) {
        giveCard(dealer);
        giveCard(dealer);
        for (final Player player : players) {
            giveCard(player);
            giveCard(player);
        }
    }
}
