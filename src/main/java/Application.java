import controller.GameController;

public class Application {

    public static void main(String[] args) {
        GameController gameController = GameController.makeWithInput();
        gameController.ready();
        gameController.play();
        gameController.printFinalGameResult();
    }
}
