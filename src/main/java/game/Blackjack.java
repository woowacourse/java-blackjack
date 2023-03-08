package game;

import domain.Dealer;
import domain.Gambler;
import domain.Player;
import domain.Players;
import domain.Result;

import java.util.Map;

import static view.InputView.printErrorMessage;
import static view.InputView.readIsHit;
import static view.OutputView.printDealerHitMessage;
import static view.OutputView.printSingleGambler;

public class Blackjack {

    private final Players players;
    private final Dealer dealer;
    private Result result;

    public Blackjack(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void play() {
        hitOrStandByPlayers();
        hitOrStandByDealer();
        createResult();
    }

    private void hitOrStandByPlayers() {
        for (Player player : players.getPlayers()) {
            hitOrStandByPlayer(player);
        }
    }

    private void hitOrStandByPlayer(Player player) {
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
        try {
            return readIsHit(player);
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getIsHit(player);
        }
    }

    private void playerHit(Player player, boolean isHit) {
        if (isHit) {
            player.pickCard();
        }
    }

    private void hitOrStandByDealer() {
        while (dealer.isDealerHit()) {
            dealerHit();
        }
    }

    private void dealerHit() {
        dealer.pickCard();
        printDealerHitMessage();
    }

    public void createResult() {
        result = new Result(players, dealer);
    }

    public Map<Gambler, Integer> getResult() {
        return result.getResult();
    }
}
