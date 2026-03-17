public class Application {

    public static void main(String[] args) {
        AppConfig config = new AppConfig();

        Blackjack blackjack = config.blackjack();
        blackjack.start();
    }
}
