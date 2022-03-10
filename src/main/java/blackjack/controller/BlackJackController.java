package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {

    public void run() {
        List<String> names = InputView.getNames();
        BlackJackGame blackJackGame = new BlackJackGame(names);
        blackJackGame.distributeFirstCards();
        OutputView.printFirstCards(blackJackGame.getDealerDto(), blackJackGame.getPlayerDtos());
    }

    public static void main(String[] args) {
        new BlackJackController().run();
    }
}
