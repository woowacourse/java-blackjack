import controller.BlackjackController;
import service.BlackjackService;

public class Application {
    public static void main(String[] args) {
        BlackjackService blackjackService = new BlackjackService();
        BlackjackController blackjackController = new BlackjackController(blackjackService);
        blackjackController.run();
    }
}
