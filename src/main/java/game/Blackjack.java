package game;

import domain.Dealer;
import domain.Gambler;
import domain.Player;
import domain.Players;
import domain.Result;
import view.InputView;

import java.util.Map;

import static view.InputView.printErrorMessage;
import static view.InputView.readIsHit;
import static view.OutputView.printDealerHitMessage;
import static view.OutputView.printSingleGambler;

public class Blackjack {

    private Result result;

    public void play(Players players, Dealer dealer) {
        hitOrStandByPlayers(players);
        hitOrStandByDealer(dealer);
        createResult(players, dealer);
    }

    private void hitOrStandByPlayers(Players players) {
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

    private void hitOrStandByDealer(Dealer dealer) {
        while (dealer.isDealerHit()) {
            dealerHit(dealer);
        }
    }

    private void dealerHit(Dealer dealer) {
        dealer.pickCard();
        printDealerHitMessage();
    }

    public void createResult(Players players, Dealer dealer) {
        result = new Result(players, dealer);
    }

    public Map<Gambler, Integer> getResult() {
        return result.getResult();
    }
}
