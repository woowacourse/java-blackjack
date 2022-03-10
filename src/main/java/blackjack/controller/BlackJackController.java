package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.view.InputView;

import java.util.List;

public class BlackJackController {

    public void run() {
        List<String> names = InputView.getNames();
        BlackJackGame blackJackGame = new BlackJackGame(names);
        blackJackGame.distributeFirstCards();
    }
}
