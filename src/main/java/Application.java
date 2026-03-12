import controller.GameController;
import service.BlackjackService;
import service.PlayerManager;

public class Application {
    public static void main(String[] args) {
        PlayerManager playerManager = new PlayerManager();
        BlackjackService blackjackService = new BlackjackService();

        GameController gameController = new GameController(blackjackService, playerManager);

        gameController.run();
    }
}
