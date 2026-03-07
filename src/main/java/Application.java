import config.AppConfig;
import controller.BlackjackController;

public class Application {

    public static void main(String[] args) {
        AppConfig config = new AppConfig();

        BlackjackController controller = config.controller();
        controller.start();
    }
}
