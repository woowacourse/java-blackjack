import controller.GameController;

public class GameApplication {

    public static void main(String[] args) {
        final GameApplicationConfig config = new GameApplicationConfig();
        final GameController gamePrepareController = config.gameController();

        gamePrepareController.start();
    }
}
