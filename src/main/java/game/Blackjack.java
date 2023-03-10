package game;

import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.Result;

import static controller.Controller.getIsHit;
import static controller.Controller.printDealerHit;
import static controller.Controller.printHitOrStandByPlayer;

public class Blackjack {
    private Result result;

    public Blackjack(Players players, Dealer dealer) {
        this.result = play(players, dealer);
    }

    public Result play(Players players, Dealer dealer) {
        hitOrStandByPlayers(players);
        hitOrStandByDealer(dealer);
        return createResult(players, dealer);
    }

    private void hitOrStandByPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            boolean isHit;
            do {
                isHit = getIsHit(player);
                hitOrStandByPlayer(player, isHit);
                printHitOrStandByPlayer(player);
            } while (isHit && isPickAble(player));
        }

    }

    private void hitOrStandByPlayer(Player player, boolean isHit) {
        playerHit(player, isHit);
    }

    private boolean isPickAble(Player player) {
        if (player.isBustedGambler()) {
            return false;
        }
        return true;
    }

    private void playerHit(Player player, boolean isHit) {
        if (isHit) {
            player.pickCard();
        }
    }

    private void hitOrStandByDealer(Dealer dealer) {
        while (dealer.isDealerHit()) {
            dealerHit(dealer);
            printDealerHit();
        }
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
