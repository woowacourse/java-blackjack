package controller;

import domain.participant.Participant;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        try {
            BlackjackService blackjackGame = BlackjackService.of(InputView.readPlayersName());

            initParticipantsHand(blackjackGame);
            runPlayersTurn(blackjackGame);
            runDealerTurn(blackjackGame);

            OutputView.printAllHands(blackjackGame.getDealer(), blackjackGame.getPlayers());
            OutputView.printParticipantsResult(blackjackGame.getResult());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
        }
    }

    private void initParticipantsHand(BlackjackService blackjackGame) {
        OutputView.printStartMessage(blackjackGame.getPlayersName());

        blackjackGame.start();

        OutputView.printDealerCard(blackjackGame.getDealer());
        OutputView.printPlayersCard(blackjackGame.getPlayers());
    }

    private void runPlayersTurn(BlackjackService blackjackGame) {
        while (blackjackGame.existNextPlayerTurn()) {
            Participant nextPlayer = blackjackGame.getNextPlayer();
            blackjackGame.nextTurn(InputView.readHit(nextPlayer));
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
