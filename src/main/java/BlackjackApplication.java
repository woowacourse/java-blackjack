import config.ControllerConfig;
import controller.BlackjackController;

public class BlackjackApplication {

    public static void main(String[] args) {
        ControllerConfig controllerConfig = new ControllerConfig();
        BlackjackController blackjackController = controllerConfig.blackjackController();
        blackjackController.start();
    }
}
