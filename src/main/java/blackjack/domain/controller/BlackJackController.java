package blackjack.domain.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.DrawCommand;
import blackjack.domain.GameMachine;
import blackjack.domain.HoldingCard;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = initBlackJackGame();
        OutputView.printInitialCardStatus(blackJackGame.getParticipantsDto());

        blackJackGame = runAllPlayersTurn(blackJackGame);
    }

    private BlackJackGame runAllPlayersTurn(BlackJackGame blackJackGame) {
        runPlayerTurn(blackJackGame);
        runDealerTurn(blackJackGame);
        return blackJackGame;
    }

    private void runPlayerTurn(BlackJackGame blackJackGame) {
        if (blackJackGame.isAllPlayerFinished()) {
            return;
        }
        String currentPlayerName = blackJackGame.getCurrentPlayerName();
        HoldingCard currentPlayerCards = blackJackGame.drawCurrentPlayer(askDrawCommand(currentPlayerName));
        OutputView.printPlayerCards(currentPlayerName, currentPlayerCards);
        runPlayerTurn(blackJackGame);
    }

    private DrawCommand askDrawCommand(String name) {
        try {
            return GameMachine.createDrawCommand(InputView.askDrawCommand(name));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return askDrawCommand(name);
        }
    }

    private void runDealerTurn(BlackJackGame blackJackGame) {
        blackJackGame.dealerFinishGame();
    }

    private BlackJackGame initBlackJackGame() {
        try {
            return GameMachine.createBlackJackGame(InputView.askPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initBlackJackGame();
        }
    }
}
