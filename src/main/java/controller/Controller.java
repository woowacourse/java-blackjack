package controller;

import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.Result;
import game.Blackjack;

import java.util.List;

import static view.InputView.printErrorMessage;
import static view.InputView.readIsHit;
import static view.InputView.readPlayerNames;
import static view.OutputView.printDealerHitMessage;
import static view.OutputView.printGamblersCards;
import static view.OutputView.printInitialPickGuideMessage;
import static view.OutputView.printResult;
import static view.OutputView.printScores;
import static view.OutputView.printSingleGambler;


public class Controller {

    public void playBlackjack() {
        Blackjack blackjack = new Blackjack(getValidPlayerNames());
        Players players = blackjack.getPlayers();
        Dealer dealer = blackjack.getDealer();

        printInitialPickGuideMessage(players);
        printGamblersCards(players, dealer);

        Result result = playBlackjack(players, dealer, blackjack);
        printScores(players, dealer);
        printResult(result);
    }

    public boolean getIsHit(Player player) {
        try {
            return readIsHit(player.getName());
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getIsHit(player);
        }
    }

    private List<String> getValidPlayerNames() {
        try {
            return readPlayerNames();
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getValidPlayerNames();
        }
    }

    private Result playBlackjack(Players players, Dealer dealer, Blackjack blackjack) {
        hitOrStandByPlayers(players, blackjack);
        hitOrStandByDealer(dealer, blackjack);
        blackjack.createResult(players, dealer);

        return blackjack.getResult();
    }

    private void hitOrStandByPlayers(Players players, Blackjack blackjack) {
        for (Player player : players.getPlayers()) {
            boolean isHit;
            do {
                isHit = getIsHit(player);
                blackjack.hitOrStandByPlayer(player, isHit);
                printSingleGambler(player);
            } while (isHit && isPickAble(player));
        }
    }

    private boolean isPickAble(Player player) {
        return !player.isBustedGambler();
    }

    public void hitOrStandByDealer(Dealer dealer, Blackjack blackjack) {
        while (dealer.isDealerHit()) {
            blackjack.hitOrStandByDealer(dealer);
            printDealerHitMessage();
        }
    }
}
