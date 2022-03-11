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

        blackJackGame = runAllPlayersTurn(blackJackGame);
        OutputView.printPlayerFinalCards(GameMachine.createPlayerFinalCardsAndScore(blackJackGame));
        ScoreResultDto finalScore = GameMachine.createFinalScore(blackJackGame);
        OutputView.printFinalScore(finalScore);

    }

    private BlackJackGame initBlackJackGame() {
        try {
            return GameMachine.createBlackJackGame(InputView.askPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initBlackJackGame();
        }
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

    private void runDealerTurn(BlackJackGame blackJackGame) {
        blackJackGame.dealerFinishGame();
    }

    private DrawCommand askDrawCommand(String name) {
        try {
            return GameMachine.createDrawCommand(InputView.askDrawCommand(name));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return askDrawCommand(name);
        }
    }
}
