package controller;

import domain.participant.Player;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

import java.util.Collections;

public class BlackjackController {

    public void run() {
        try {
            BlackjackService blackjackGame = BlackjackService.of(InputView.readPlayersName(), Collections::shuffle);

            prepare(blackjackGame);
            start(blackjackGame);

            OutputView.printAllHands(blackjackGame.getDealer(), blackjackGame.getPlayers());
            OutputView.printParticipantsResult(blackjackGame.getParticipantsResult());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
        }
    }

    private void prepare(BlackjackService blackjackGame) {
        betPlayers(blackjackGame);
        OutputView.printStartMessage(blackjackGame.getPlayersName());
        OutputView.printDealerCard(blackjackGame.getDealer());
        OutputView.printPlayersCard(blackjackGame.getPlayers());
    }

    private void betPlayers(BlackjackService blackjackGame) {
        for (Player player : blackjackGame.getPlayers()) {
            player.bet(InputView.readBettingAmount(player.getName()));
        }
    }

    private void start(BlackjackService blackjackGame) {
        runPlayersTurn(blackjackGame);
        runDealerTurn(blackjackGame);
    }

    private void runPlayersTurn(BlackjackService blackjackGame) {
        while (blackjackGame.getNextPlayer().isPresent()) {
            Player nextPlayer = blackjackGame.getNextPlayer().get();

            blackjackGame.playNextTurnPlayer(nextPlayer, InputView.readHit(nextPlayer));

            OutputView.printPlayerCard(nextPlayer);
        }
    }

    private void runDealerTurn(BlackjackService blackjackGame) {
        if (!blackjackGame.isDealerStand()) {
            blackjackGame.dealerTurn();
            OutputView.printDealerHit();
        }
    }
}
