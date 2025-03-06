package controller;

import static view.Response.YES;

import domain.Game;
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
        Game game = initializeGame(usernames);
        for (String username : usernames) {
            askPlayer(game, username);
        }
        askDealer(game);
        outputView.printFinalState(game.getPlayersInfo(), game.getDealer());
        outputView.printGameStatistics(game.getGameStatistics());
    }

    private Game initializeGame(List<String> usernames) {
        Game game = new Game(usernames);
        game.distributeStartingHands();
        outputView.printInitialState(game.getPlayersInfo(), game.getDealerOneCard());
        return game;
    }

    private void askPlayer(Game game, String username) {
        while (game.isPlayerDrawable(username) && inputView.getUserResponse(username) == YES) {
            game.giveCardToPlayer(username, 1);
            outputView.printGamerCards(username, game.getPlayerCards(username));
        }
    }

    private void askDealer(Game game) {
        while (game.isDealerDrawable()) {
            game.giveCardToDealer(1);
            outputView.printDealerDrawMoreCard();
        }
    }
}
