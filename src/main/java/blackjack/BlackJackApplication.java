package blackjack;

import blackjack.controller.BlackJackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BlackJackGame blackJackGame = new BlackJackGame(inputView);
        blackJackGame.play(inputView, outputView);
    }
}
