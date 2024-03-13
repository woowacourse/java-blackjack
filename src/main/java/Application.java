import game.BlackjackGame;
import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) {
        new BlackjackGame(new InputView(), new ResultView()).play();
    }
}
