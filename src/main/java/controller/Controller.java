package controller;

import domain.Dealer;
import domain.Players;
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
        //TODO: getResult라는 네이밍이 혼동을 주진 않을까요? 사실상 생성이자 getter이기도 해서.. 어떻게 생각하시나요?
        printResult(blackjack.getResult(players, dealer).getResult());
    }
}
