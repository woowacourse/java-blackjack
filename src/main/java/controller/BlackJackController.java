package controller;

import static view.Response.YES;

import domain.Game;
import domain.PlayerName;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> usernames = inputView.insertUsernames();
        List<PlayerName> playerNames = usernames.stream()
                .map(PlayerName::new)
                .toList();

        Game game = initializeGame(playerNames);
        for (PlayerName playerName : playerNames) {
            askPlayer(game, playerName);
        }
        askDealer(game);
        outputView.printFinalState(game.getPlayersInfo(), game.getDealer());
        outputView.printGameStatistics(game.getGameStatistics());
    }

    private Game initializeGame(List<PlayerName> playerNames) {
        Game game = new Game(playerNames);
        game.distributeStartingHands();
        outputView.printInitialState(game.getPlayersInfo(), game.getDealerOneCard());
        return game;
    }

    private void askPlayer(Game game, PlayerName playerName) {
        while (game.isPlayerDrawable(playerName) && inputView.getUserResponse(playerName.username()) == YES) {
            game.giveCardToPlayer(playerName, 1);
            outputView.printGamerCards(playerName.username(), game.getPlayerCards(playerName));
        }
    }

    private void askDealer(Game game) {
        while (game.isDealerDrawable()) {
            game.giveCardToDealer(1);
            outputView.printDealerDrawMoreCard();
        }
    }
}
