package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class BlackjackController {
    public void run() {
        BlackjackGame blackjackGame = setUpBlackjackGame();
        betAllPlayers(blackjackGame);
        blackjackGame.handOutInitialCards();
        showInitialStatus(blackjackGame);
        proceedPlayersRound(blackjackGame);
        proceedDealerRound(blackjackGame);
        OutputView.printCardsWithTotalValue(blackjackGame.getUsers());
        long dealerProfit = blackjackGame.getDealerProfit();
        Map<Player, Long> profitResult = blackjackGame.getProfitsByPlayer();
        OutputView.printProfitResult(dealerProfit, profitResult);
    }

    private BlackjackGame setUpBlackjackGame() {
        OutputView.printInputNames();
        return BlackjackGame.generateByUser(InputView.inputNames());
    }

    private void betAllPlayers(BlackjackGame blackjackGame) {
        List<String> playersName = blackjackGame.getPlayersName();
        for (String name : playersName) {
            OutputView.printInputMoneyByPlayer(name);
            blackjackGame.betByPlayer(name, InputView.inputLong());
        }
    }

    private void showInitialStatus(BlackjackGame blackjackGame) {
        OutputView.printDistribute(blackjackGame.getDealer(), blackjackGame.getPlayersName());
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
