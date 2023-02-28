package controller;

import model.BlackJackGame;
import model.Player;
import model.Players;
import model.Result;
import ui.input.InputView;
import ui.output.OutputView;

import java.util.Arrays;

public class BlackJackController {

    private static final String RECEIVE_CARD_COMMAND = "y";
    
    private final BlackJackGame blackJackGame;

    public BlackJackController(final BlackJackGame blackJackGame) {
        this.blackJackGame = blackJackGame;
    }

    public void init() {
        Players players = new Players(Arrays.asList(InputView.getPlayersName().split(",")));
        Result result = new Result(players);

        divideInitialCard(players, result);

        OutputView.printDivideInitialCards(result);

        for (Player player : players.getPlayers()) {
            final String receiveCardCommand = InputView.getReceiveCardCommand(player.getName());

            receiveMoreCard(result, player, receiveCardCommand);
        }
        
        
    }

    private void receiveMoreCard(final Result result, final Player player, final String receiveCardCommand) {
        if (RECEIVE_CARD_COMMAND.equals(receiveCardCommand)) {
            blackJackGame.divideCard(result, player);
        }
    }

    private void divideInitialCard(final Players players, final Result result) {
        for (Player player : players.getPlayers()) {
            divideInitialCard(result, player);
        }
    }

    private void divideInitialCard(final Result result, final Player player) {
        blackJackGame.divideCard(result, player);
        blackJackGame.divideCard(result, player);
    }

}
