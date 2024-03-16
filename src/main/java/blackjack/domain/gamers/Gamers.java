package blackjack.domain.gamers;

import blackjack.domain.card.Card;
import java.util.List;

public class Gamers {

    private final Dealer dealer;
    private final Players players;

    public Gamers(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void drawInitialGamersHand() {
        dealer.drawInitialHand();
        players.drawInitialHand(dealer);
    }

    public Card openDealerFirstCard() {
        return dealer.openFirstCard();
    }

    public boolean canPlayerHit(final Name name) {
        return players.canDraw(name);
    }

    public void hitPlayer(final Name name) {
        players.draw(dealer.drawPlayerCard(), name);
    }

    public boolean canDealerHit() {
        return dealer.canDraw();
    }

    public void hitDealer() {
        dealer.draw();
    }

    public Player findPlayerBy(final Name name) {
        return players.findBy(name);
    }

    public List<Name> playerNames() {
        return players.names();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
