import controller.GameController;
import domain.Game;
import view.InputView;

public class Application {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.run();
    }
}
