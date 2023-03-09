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

    private Result result;

    public void play(Players players, Dealer dealer) {
        hitOrStandByPlayers(players);
        hitOrStandByDealer(dealer);
        createResult(players, dealer);
    }

    private void hitOrStandByPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            boolean isHit;
            do {
                isHit = getIsHit(player);
                hitOrStandByPlayer(player, isHit);
            } while (isHit && isPickAble(player));
        }
    }

    public void hitOrStandByPlayer(Player player, boolean isHit) {
        playerHit(player, isHit);
        printSingleGambler(player);
    }

    private boolean isPickAble(Player player) {
        if (player.isBustedGambler()) {
            return false;
        }
        return true;
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
