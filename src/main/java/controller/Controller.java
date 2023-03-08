package controller;

import domain.Dealer;
import domain.Players;
import domain.Result;
import game.Blackjack;

import static view.OutputView.printGamblersCards;
import static view.OutputView.printInitialPickGuideMessage;
import static view.OutputView.printResult;
import static view.OutputView.printScores;


public class Controller {
    private final Blackjack blackjack;

    public Controller(Players players, Dealer dealer) {
        this.blackjack = new Blackjack(players, dealer);
    }

    public void playGame(Players players, Dealer dealer) {
        printInitialPickGuideMessage(players);
        printGamblersCards(players, dealer);

        blackjack.play();

        printScores(players, dealer);
        printResult(blackjack.getResult());
    }
}
