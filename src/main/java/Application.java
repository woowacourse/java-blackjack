import controller.BlackJackController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        BlackJackController blackJackController = new BlackJackController(appConfig.inputView(),
                appConfig.outputView());
        blackJackController.run();
    }
}
