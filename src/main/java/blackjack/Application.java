package blackjack;

import blackjack.view.InputView;
import blackjack.view.MessageResolver;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView(new InputMapper());
        OutputView outputView = new OutputView(new MessageResolver());

        BlackJackGame blackJackGame = new BlackJackGame(inputView, outputView);
        blackJackGame.run();
    }
}
