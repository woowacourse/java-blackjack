import controller.BlackjackController;
import util.ServiceLocator;

public class application {
    public static void main(String[] args) {
        final BlackjackController blackjackController = ServiceLocator.getBlackjackController();
        blackjackController.run();
    }
}
