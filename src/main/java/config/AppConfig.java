package config;

import controller.Controller;
import domain.DrawStrategy;
import domain.factory.BlackJackFactory;
import domain.strategy.RandomStrategy;

public class AppConfig {

    public Controller blackJackController() {
        return new Controller(blackJackFactory().openGameTable());
    }

    private BlackJackFactory blackJackFactory() {
        return BlackJackFactory.basedOn(drawStrategy());
    }

    private DrawStrategy drawStrategy() {
        return new RandomStrategy();
    }
}
