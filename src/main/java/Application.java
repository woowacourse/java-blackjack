import domain.BlackJackGame;
import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        BlackJackGame blackJackGame = new BlackJackGame(inputView, resultView);
        blackJackGame.play();
    }
}
