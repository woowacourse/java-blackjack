import controller.BlackjackController;
import controller.factory.BlackjackControllerFactory;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackControllerFactory blackjackControllerFactory = new BlackjackControllerFactory();
        BlackjackController blackjackController = blackjackControllerFactory.blackjackController();
        blackjackController.start();
    }
}
