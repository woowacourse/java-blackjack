import game.BlackjackGame;
import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) throws Exception {
        try (InputView inputView = new InputView()){
            new BlackjackGame(inputView, new ResultView()).play();
        }
    }
}
