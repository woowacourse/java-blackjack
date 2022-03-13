package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.DrawCommand;
import blackjack.domain.GameMachine;
import blackjack.domain.card.HoldingCard;
import blackjack.dto.ScoreResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = initBlackJackGame();
        OutputView.printInitialCardStatus(blackJackGame.getParticipantsDto());

        runAllPlayersTurn(blackJackGame);
        OutputView.printPlayerFinalCards(GameMachine.createPlayerFinalCardsAndScore(blackJackGame));
        ScoreResultDto finalScore = GameMachine.createFinalScore(blackJackGame);
        OutputView.printFinalScore(finalScore);

    }

    private BlackJackGame initBlackJackGame() {
        try {
            return new BlackJackGame(InputView.askPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initBlackJackGame();
        }
    }

    private void runAllPlayersTurn(BlackJackGame blackJackGame) {
        runPlayerTurn(blackJackGame);
        runDealerTurn(blackJackGame);
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

    private void runDealerTurn(BlackJackGame blackJackGame) {
        int gainCardCount = blackJackGame.dealerFinishGame();
        OutputView.printDealerGainCardCount(gainCardCount);
    }

    private DrawCommand askDrawCommand(String name) {
        try {
            return DrawCommand.from(InputView.askDrawCommand(name));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return askDrawCommand(name);
        }
    }
}
