import config.AppConfig;
import controller.Controller;

public class Application {

    public static void main(String[] args) {
        blackjackController().run();
    }

    private static Controller blackjackController() {
        AppConfig config = new AppConfig();
        return config.preController();
    }
}

