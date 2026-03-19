package config;

import controller.BlackJackController;
import domain.gameplaying.DrawStrategy;
import domain.gameplaying.Participants;
import domain.gameplaying.strategy.OnlyOneDeckDrawStrategy;
import domain.result.ScoreBoard;
import service.BlackJackCommandService;
import service.BlackJackQueryService;

public class AppConfig {

    private static final DrawStrategy sharedDeck = new OnlyOneDeckDrawStrategy();

    public BlackJackController blackJackController() {
        Participants participants = Participants.onlyDealer(sharedDeck);
        ScoreBoard scoreBoard = new ScoreBoard();

        BlackJackCommandService commandService = new BlackJackCommandService(participants, scoreBoard);
        BlackJackQueryService queryService = new BlackJackQueryService(participants, scoreBoard);

        return new BlackJackController(commandService, queryService);
    }
}
