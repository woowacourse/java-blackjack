package blackjack.controller;

import blackjack.view.InputView;

public class GameController {
    public GameController() {

    }

    public void run() {
        String[] names = InputView.inputPlayerName();
        System.out.println(names[0] + names[1]);
        boolean moreCard = InputView.inputOneMoreCard("jack");
        System.out.println(moreCard);
    }
}