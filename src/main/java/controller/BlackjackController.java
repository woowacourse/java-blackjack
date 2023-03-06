package controller;

import domain.participant.Participant;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        try {
            BlackjackService blackjackGame = BlackjackService.of(InputView.readPlayersName());

            prepare(blackjackGame);
            start(blackjackGame);

            OutputView.printAllHands(blackjackGame.getDealer(), blackjackGame.getPlayers());
            OutputView.printParticipantsResult(blackjackGame.getResult());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
        }
    }

    private void prepare(BlackjackService blackjackGame) {
        OutputView.printStartMessage(blackjackGame.getPlayersName());
        OutputView.printDealerCard(blackjackGame.getDealer());
        OutputView.printPlayersCard(blackjackGame.getPlayers());
    }

    private void start(BlackjackService blackjackGame) {
        runPlayersTurn(blackjackGame);
        runDealerTurn(blackjackGame);
    }

    private void runPlayersTurn(BlackjackService blackjackGame) {
        while (blackjackGame.getNextPlayer().isPresent()) {
            Participant nextPlayer = blackjackGame.getNextPlayer().get();

            blackjackGame.nextTurn(nextPlayer, InputView.readHit(nextPlayer));

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
