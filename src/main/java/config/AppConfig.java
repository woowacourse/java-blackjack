package config;

import controller.BlackJackController;
import domain.gameplaying.BlackJackDeck;
import domain.gameplaying.Participants;
import domain.gameplaying.strategy.OnlyOneDeck;
import domain.result.ScoreBoard;
import service.BlackJackCommandService;
import service.BlackJackQueryService;

public class AppConfig {

    private static final BlackJackDeck sharedDeck = new OnlyOneDeck();

    public BlackJackController blackJackController() {
        Participants participants = Participants.onlyDealer(sharedDeck);
        ScoreBoard scoreBoard = new ScoreBoard();

        BlackJackCommandService commandService = new BlackJackCommandService(participants, scoreBoard);
        BlackJackQueryService queryService = new BlackJackQueryService(participants, scoreBoard);

        return new BlackJackController(commandService, queryService);
    }
}
