package controller;

import Game.Blackjack;
import domain.Dealer;
import domain.Player;
import domain.Players;

import static view.OutputView.*;

public class Controller {
    private Blackjack blackjack;

    public Controller(){
        this.blackjack = new Blackjack();
    }

    public void playGame(Players players, Dealer dealer) {
        printInitialPickGuideMessage(players);
        printGamblersCards(players, dealer);

        blackjack.play(players,  dealer);

        printScores(players, dealer);
        printResult(blackjack.getResult());
    }

    private void printScores(Players players, Dealer dealer) {
        printScore(dealer);
        for (Player player : players.getPlayers()) {
            printScore(player);
        }
    }
}
