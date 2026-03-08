import controller.GameController;
import service.GameService;

public class Application {
    public static void main(String[] args) {
        GameService gameService = new GameService();
        GameController gameController = new GameController(gameService);

        gameController.run();
    }
}
