import factory.BlackjackControllerFactory;
import controller.BlackjackController;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackControllerFactory blackjackControllerFactory = new BlackjackControllerFactory();
        BlackjackController blackjackController = blackjackControllerFactory.blackjackController();
        blackjackController.start();
    }
}
