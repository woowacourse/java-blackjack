import game.BlackJackGame;
import view.InputView;
import view.OutputView;

public class BlackJackMain {

    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BlackJackGame blackJackGame = new BlackJackGame(inputView, outputView);
        blackJackGame.play();
    }
}
