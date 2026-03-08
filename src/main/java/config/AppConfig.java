package config;

import controller.BlackJackController;
import domain.DrawStrategy;
import domain.strategy.RandomStrategy;
import repository.GameTableRepository;
import service.BlackJackCommandService;
import service.BlackJackQueryService;

public class AppConfig {

    public BlackJackController blackJackController() {
        GameTableRepository gameTableRepository = new GameTableRepository();

        BlackJackCommandService commandService = new BlackJackCommandService(gameTableRepository, drawStrategy());
        BlackJackQueryService queryService = new BlackJackQueryService(gameTableRepository);

        return new BlackJackController(commandService, queryService);
    }

    private DrawStrategy drawStrategy() {
        return new RandomStrategy();
    }
}
