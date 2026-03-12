package config;

import controller.BlackJackController;
import domain.gameplaying.DrawStrategy;
import domain.gameplaying.strategy.OneDeckStrategy;
import repository.ParticipantRepository;
import repository.ScoreRepository;
import service.BlackJackCommandService;
import service.BlackJackQueryService;

public class AppConfig {

    public BlackJackController blackJackController() {
        ParticipantRepository participantRepository = new ParticipantRepository();
        ScoreRepository scoreRepository = new ScoreRepository();

        BlackJackCommandService commandService = setupCommandService(participantRepository, scoreRepository);
        BlackJackQueryService queryService = new BlackJackQueryService(participantRepository, scoreRepository);

        return new BlackJackController(commandService, queryService);
    }

    private BlackJackCommandService setupCommandService(ParticipantRepository participantRepository,
                                                        ScoreRepository scoreRepository) {
        return new BlackJackCommandService(participantRepository, scoreRepository, drawStrategy());
    }

    private DrawStrategy drawStrategy() {
        return new OneDeckStrategy();
    }
}
