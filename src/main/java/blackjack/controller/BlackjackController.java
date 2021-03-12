package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.result.ResultBoard;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.stream.IntStream;

public class BlackjackController {
    public void run() {
        BlackjackGame blackjackGame = setUpBlackjackGame();
        blackjackGame.handOutInitialCards();
        showInitialStatus(blackjackGame);
        proceedPlayersRound(blackjackGame);
        proceedDealerRound(blackjackGame);
        OutputView.printCardsWithTotalValue(blackjackGame.getUsers());
        ResultBoard resultBoard = blackjackGame.generateResultBoard();
        OutputView.printResultBoard(resultBoard);
    }

    private BlackjackGame setUpBlackjackGame() {
        OutputView.printInputNames();
        return BlackjackGame.generateByUser(InputView.inputNames());
    }

    private void showInitialStatus(BlackjackGame blackjackGame) {
        OutputView.printDistribute(blackjackGame.getDealer(), blackjackGame.getPlayers());
        OutputView.printDealerCard(blackjackGame.getDealer());
        OutputView.printPlayersCards(blackjackGame.getPlayers());
    }

    private void proceedPlayersRound(BlackjackGame blackjackGame) {
        while (blackjackGame.isNotFinishPlayersRound()) {
            Player currentPlayer = blackjackGame.getCurrentPlayer();
            OutputView.printAskOneMoreCard(currentPlayer);
            blackjackGame.proceedPlayersRound(InputView.inputString());
            OutputView.printPlayerCards(currentPlayer);
        }
    }

    private void proceedDealerRound(BlackjackGame blackjackGame) {
        int roundCount = blackjackGame.proceedDealerRound();
        IntStream.range(0, roundCount)
                .forEach(i -> OutputView.printDealerDrawable());
        OutputView.printDealerNotDrawable();
    }
}
