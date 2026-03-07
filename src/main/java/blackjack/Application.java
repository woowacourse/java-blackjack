package blackjack;

import blackjack.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        appConfig.blackJack().start();
    }
}
