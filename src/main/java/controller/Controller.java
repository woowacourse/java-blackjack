package controller;

import Game.Blackjack;
import domain.Dealer;
import domain.Players;

import static view.OutputView.*;

public class Controller {
    private Blackjack blackjack;
    private Players players;
    private Dealer dealer;

    public Controller(Players players, Dealer dealer) {
        this.blackjack = new Blackjack(players, dealer);
        this.players = players;
        this.dealer = dealer;
    }

    public void playGame() {
        printInitialPickGuideMessage(players);
        printGamblersCards(players, dealer);

        blackjack.play();

        printScores(players, dealer);
        printResult(blackjack.getResultMap());
    }
}
