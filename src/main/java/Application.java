import controller.GameController;
import service.BlackjackService;

public class Application {
    public static void main(String[] args) {
        BlackjackService blackjackService = new BlackjackService();

        GameController gameController = new GameController(blackjackService);

        gameController.run();
    }
}
