package config;

import controller.BlackJackController;
import domain.gameplaying.BlackJackDeck;
import domain.gameplaying.strategy.OnlyOneDeck;
import repository.ParticipantRepository;
import repository.ScoreRepository;
import service.BlackJackCommandService;
import service.BlackJackQueryService;

public class AppConfig {

    private final BlackJackDeck blackJackDeck = new OnlyOneDeck();

    public BlackJackController blackJackController() {
        ParticipantRepository participantRepository = new ParticipantRepository();
        ScoreRepository scoreRepository = new ScoreRepository();

        BlackJackCommandService commandService = setupCommandService(participantRepository, scoreRepository);
        BlackJackQueryService queryService = new BlackJackQueryService(participantRepository, scoreRepository);

        return new BlackJackController(commandService, queryService);
    }

    private BlackJackCommandService setupCommandService(ParticipantRepository participantRepository,
                                                        ScoreRepository scoreRepository) {
        return new BlackJackCommandService(participantRepository, scoreRepository, new OnlyOneDeck());
    }

    private BlackJackDeck sharedDeck() {
        return this.blackJackDeck;
    }
}
