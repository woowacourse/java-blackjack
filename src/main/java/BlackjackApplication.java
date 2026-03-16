import domain.game.BlackjackGameManager;
import factory.BlackjackFactory;
import controller.BlackjackController;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackFactory blackjackFactory = new BlackjackFactory();
        BlackjackController blackjackController = blackjackFactory.blackjackController();
        BlackjackGameManager blackjackGameManager = blackjackFactory.blackjackGameManager();
        blackjackController.start(blackjackGameManager);
    }
}
