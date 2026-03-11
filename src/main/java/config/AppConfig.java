package config;

import controller.BlackJackController;
import domain.BlackJackFactory;
import domain.DrawStrategy;
import domain.ParticipantFactory;
import domain.strategy.OneDeckStrategy;
import repository.GameTableRepository;
import repository.ParticipantRepository;
import repository.ScoreRepository;
import service.BlackJackCommandService;
import service.BlackJackQueryService;

public class AppConfig {

    public BlackJackController blackJackController() {
//        GameTableRepository gameTableRepository = new GameTableRepository();

//        BlackJackCommandService commandService = new BlackJackCommandService(gameTableRepository, blackJackFactory());
//        BlackJackQueryService queryService = new BlackJackQueryService(gameTableRepository);


        ParticipantRepository participantRepository = new ParticipantRepository();
        ScoreRepository scoreRepository = new ScoreRepository();

        BlackJackCommandService commandService = new BlackJackCommandService(
                participantRepository,
                scoreRepository,
                participantFactory());

        BlackJackQueryService queryService = new BlackJackQueryService(participantRepository, scoreRepository);

        return new BlackJackController(commandService, queryService);
    }

    private DrawStrategy drawStrategy() {
        return new OneDeckStrategy();
    }

    private BlackJackFactory blackJackFactory() {
        return BlackJackFactory.basedOn(drawStrategy());
    }

    private ParticipantFactory participantFactory() {
        return ParticipantFactory.basedOn(drawStrategy());
    }
}
