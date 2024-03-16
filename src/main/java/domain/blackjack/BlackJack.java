package domain.blackjack;

import domain.Bet.BetResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;


public class BlackJack {

    public static final int BEGIN_DRAW_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    public BlackJack(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void beginDealing() {
        dealer.receiveCard(dealer.draw(BEGIN_DRAW_COUNT));

        for (Player player : players.getValue()) {
            player.receiveCard(dealer.draw(BEGIN_DRAW_COUNT));
        }
    }

    public int dealerHit() {
        int count = 0;
        while (dealer.canHit()) {
            dealer.receiveCard(dealer.draw());
            count++;
        }
        return count;
    }

    public BetResult makeBetResult() {
        return BetResult.of(players, dealer);
    }


    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
