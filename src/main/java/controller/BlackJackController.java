package controller;

import model.BlackJackGame;
import model.Player;
import model.Players;
import model.Result;
import ui.input.InputView;
import ui.output.OutputView;

import java.util.Arrays;

import static model.Player.DEADLER;

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
            while (canReceiveMoreCard(result, player)) {
                receiveMoreCard(result, player);
                OutputView.printPlayerCardsInfo(player, result);
            }
        }
        dealerReceiveCard(result);
    }

    private static boolean canReceiveMoreCard(final Result result, final Player player) {
        return !DEADLER.equals(player)
                && result.canPlayerReceiveCard(player)
                && RECEIVE_CARD_COMMAND.equals(InputView.getReceiveCardCommand(player.getName()));
    }

    private void receiveMoreCard(final Result result, final Player player) {
        blackJackGame.divideCard(result, player);
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

    private void dealerReceiveCard(Result result) {
        if (result.canDealerReceiveCard()) {
            OutputView.printDealerGetCard();
            blackJackGame.divideCard(result, DEADLER);
        }
    }

}
