package blackjack;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResults;

public class BlackjackBoard {

    private final Dealer dealer;
    private final Players players;

    public BlackjackBoard(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void dealInitialCards() {
        dealer.dealInitialCards(players);
    }

    public void hitDealer() {
        dealer.drawAndReceive();
    }

    public void hitPlayer(final Player player) {
        dealer.dealCardTo(player);
    }

    public void stayPlayer(final Player player) {
        player.stay();
    }

    public boolean dealerCanReceiveCard() {
        return dealer.canReceiveCard();
    }

    public GameResults calculateResults() {
        return GameResults.calculate(players, dealer);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
