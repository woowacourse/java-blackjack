
public class Application {

    public static void main(String[] args) {
        BlackjackConfig blackjackConfig = new BlackjackConfig();
        BlackjackApplication blackjackApplication = blackjackConfig.blackjackApplication();
        blackjackApplication.run();
    }
}
