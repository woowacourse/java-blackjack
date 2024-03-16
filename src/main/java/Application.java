import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) {
        GameAndViewConnector gameAndViewConnector = new GameAndViewConnector(new InputView(), new ResultView());
        gameAndViewConnector.gameStart();
    }
}
