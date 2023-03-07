package game;

import domain.Dealer;
import domain.Gambler;
import domain.Player;
import domain.Players;

import java.util.Map;

import static view.InputView.printErrorMessage;
import static view.InputView.readIsHit;
import static view.OutputView.printDealerHitMessage;
import static view.OutputView.printSingleGambler;

public class Blackjack {

    private final Players players;
    private final Dealer dealer;

    public Blackjack(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void play() {
        playersHitOrStand();
        dealerHitOrStand();
    }

    private void playersHitOrStand() {
        for (Player player : players.getPlayers()) {
            playerHitOrStand(player);
        }
    }

    private void playerHitOrStand(Player player) {
        boolean isHit;
        do {
            isHit = getIsHit(player);
            playerHit(player, isHit);
            printSingleGambler(player);
            isHit = isPickAble(player, isHit);
        } while (isHit);
    }

    private boolean isPickAble(Player player, boolean isHit) {
        if (player.isBustedGambler()) {
            isHit = false;
        }
        return isHit;
    }

    private boolean getIsHit(Player player) {
        boolean isHit;
        try {
            isHit = readIsHit(player);
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            isHit = getIsHit(player);
        }
        return isHit;
    }

    private void playerHit(Player player, boolean isHit) {
        if (isHit) {
            player.pickCard();
        }
    }

    private void dealerHitOrStand() {
        while (dealer.getScore() <= dealer.getPickBoundary()) {
            dealerHit();
        }
    }

    private void dealerHit() {
        dealer.pickCard();
        printDealerHitMessage();
    }

    public Result getResult(Players players, Dealer dealer){
        return new Result(players,dealer);
    }
}
