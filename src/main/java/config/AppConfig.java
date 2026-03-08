package config;

import controller.BlackJackController;
import controller.Controller;
import domain.DrawStrategy;
import domain.factory.BlackJackFactory;
import domain.strategy.RandomStrategy;
import repository.ParticipantRepository;
import repository.ScoreRepository;
import service.BlackJackCommandService;
import service.BlackJackQueryService;

public class AppConfig {

    public BlackJackController blackJackController() {
        ParticipantRepository participantRepository = new ParticipantRepository();
        ScoreRepository scoreRepository = new ScoreRepository();

        BlackJackCommandService commandService = new BlackJackCommandService(participantRepository, scoreRepository);
        BlackJackQueryService queryService = new BlackJackQueryService(participantRepository, scoreRepository);

        return new BlackJackController(commandService, queryService);
    }

    public Controller preController() {
        return new Controller(blackJackFactory().openGameTable());
    }

    private BlackJackFactory blackJackFactory() {
        return BlackJackFactory.basedOn(drawStrategy());
    }

    private DrawStrategy drawStrategy() {
        return new RandomStrategy();
    }
}
