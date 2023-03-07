package controller;

import Game.Blackjack;
import domain.Dealer;
import domain.Players;

import static view.OutputView.*;

public class Controller {
    private Blackjack blackjack;

    public Controller(Players players, Dealer dealer) {
        this.blackjack = new Blackjack(players, dealer);
    }

    public void playGame(Players players, Dealer dealer) {
        printInitialPickGuideMessage(players);
        printGamblersCards(players, dealer);

        blackjack.play();

        printScores(players, dealer);
        printResult(blackjack.getResultMap());
    }
}
