import domain.game.BlackjackGameManager;
import factory.BlackjackControllerFactory;
import controller.BlackjackController;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackControllerFactory blackjackControllerFactory = new BlackjackControllerFactory();
        BlackjackController blackjackController = blackjackControllerFactory.blackjackController();
        BlackjackGameManager blackjackGameManager = blackjackControllerFactory.blackjackGameManager();
        blackjackController.start(blackjackGameManager);
    }
}
