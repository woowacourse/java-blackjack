package controller;

import domain.Dealer;
import domain.Gambler;
import domain.Player;
import domain.Players;
import domain.Result;
import game.Blackjack;

import java.util.Map;

import static view.InputView.printErrorMessage;
import static view.InputView.readIsHit;
import static view.OutputView.printDealerHitMessage;
import static view.OutputView.printGamblersCards;
import static view.OutputView.printInitialPickGuideMessage;
import static view.OutputView.printResult;
import static view.OutputView.printScores;
import static view.OutputView.printSingleGambler;


public class Controller {
    private final Blackjack blackjack;

    public Controller(Players players, Dealer dealer) {
        this.blackjack = new Blackjack(players, dealer);
    }

    public void playGame(Players players, Dealer dealer) {
        printInitialPickGuideMessage(players);
        printGamblersCards(players, dealer);

        final Result result = blackjack.play(players, dealer);

        printScores(players, dealer);
        printResult(result);
    }
}
