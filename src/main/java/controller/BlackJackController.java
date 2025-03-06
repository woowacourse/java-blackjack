package controller;

import static view.Response.NO;
import static view.Response.YES;

import domain.Game;
import java.util.List;
import view.InputView;
import view.OutputView;
import view.Response;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> usernames = inputView.insertUsernames();
        Game game = new Game(usernames);
        game.distributeStartingHands();
        outputView.printInitialState(game.getPlayersInfo(), game.getDealerOneCard());

        for (String username : usernames) {
            while (game.canPlayerGetMoreCard(username)) {
                Response response = inputView.getUserResponse(username);
                if (response == YES) {
                    game.giveCardToPlayer(username, 1);
                    outputView.printGamerCards(username, game.getPlayerCards(username));
                }
                if (response == NO) {
                    break;
                }
            }
        }

        while (game.canDealerGetMoreCard()) {
            game.giveCardToDealer(1);
            outputView.printDealerDrawMoreCard();
        }

        outputView.printFinalState(game.getPlayersInfo(), game.getDealer());

        outputView.printGameStatistics(game.getGameStatistics());
    }
}
