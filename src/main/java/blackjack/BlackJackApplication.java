package blackjack;

import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        try {
            BlackJackGame blackJackGame = new BlackJackGame();
            blackJackGame.play();
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printFatalErrorMessage(e.getMessage());
        }
    }
}
