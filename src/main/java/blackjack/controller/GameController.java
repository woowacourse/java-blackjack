package blackjack.controller;

import blackjack.domain.Name;
import blackjack.domain.human.Player;
import blackjack.view.InputView;

public class GameController {
    public GameController() {

    }

    public void run() {
        String[] names = InputView.inputPlayerName();
        System.out.println(names[0] + names[1]);
        for (String name : names) {
            Player.of(Name.of(name));
        }
        boolean moreCard = InputView.inputOneMoreCard("jack");
        System.out.println(moreCard);
    }
}