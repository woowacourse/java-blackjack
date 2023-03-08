package controller;

import domain.Dealer;
import domain.Player;
import domain.Players;
import game.Blackjack;

import static view.InputView.readIsHit;
import static view.OutputView.printGamblersCards;
import static view.OutputView.printInitialPickGuideMessage;
import static view.OutputView.printResult;
import static view.OutputView.printScores;


public class Controller {
    private final Blackjack blackjack;

    public Controller() {
        this.blackjack = new Blackjack();
    }

    public void playGame(Players players, Dealer dealer) {
        printInitialPickGuideMessage(players);
        printGamblersCards(players, dealer);

        blackjack.play(players, dealer);

        printScores(players, dealer);
        printResult(blackjack.getResult());
    }
}
