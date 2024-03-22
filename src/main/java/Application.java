import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager(new InputView(), new ResultView());
        gameManager.start();
    }
}
