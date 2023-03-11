package game;

import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.Result;


public class Blackjack {

    private final Players players;
    private final Dealer dealer;
    private Result result;

    public Blackjack(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void hitOrStandByPlayer(Player player, boolean isHit) {
        if (isHit) {
            player.pickCard();
        }
    }

    public void hitOrStandByDealer(Dealer dealer) {
        dealerHit(dealer);
    }

    private void dealerHit(Dealer dealer) {
        dealer.pickCard();
    }

    public Result createResult(Players players, Dealer dealer) {
        this.result = new Result(players, dealer);
        return result;
    }

    public Result getResult() {
        return result;
    }
}
