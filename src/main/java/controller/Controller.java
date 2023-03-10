package controller;

import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.Result;
import game.Blackjack;

import static controller.EntityCreator.createBlackjack;
import static view.InputView.printErrorMessage;
import static view.InputView.readIsHit;
import static view.OutputView.printDealerHitMessage;
import static view.OutputView.printGamblersCards;
import static view.OutputView.printInitialPickGuideMessage;
import static view.OutputView.printResult;
import static view.OutputView.printScores;
import static view.OutputView.printSingleGambler;


public class Controller {

    public void playBlackjack(Players players, Dealer dealer) {
        printInitialPickGuideMessage(players);
        printGamblersCards(players, dealer);

        Blackjack blackjack = createBlackjack(players,dealer);
        Result result = blackjack.getResult();

        printScores(players, dealer);
        printResult(result);
    }


    public static boolean getIsHit(Player player) {
        try {
            return readIsHit(player);
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getIsHit(player);
        }
    }

    public static void printHitOrStandByPlayer(Player player) {
        printSingleGambler(player);
    }

    public static void printDealerHit() {
        printDealerHitMessage();
    }
}
