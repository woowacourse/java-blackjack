package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.result.Answer;
import blackjack.domain.result.ResultBoard;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

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
        List<String> playersNames = InputView.inputNames();
        return BlackjackGame.generateByUser(playersNames);
    }

    private void showInitialStatus(BlackjackGame blackjackGame) {
        OutputView.printDistribute(blackjackGame.getDealer(), blackjackGame.getPlayers());
        OutputView.printDealerCard(blackjackGame.getDealer());
        OutputView.printPlayersCards(blackjackGame.getPlayers());
    }

    private void proceedPlayersRound(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();
        for (Player player : players.getPlayers()) {
            askWantToHit(player, blackjackGame);
        }
    }

    private void askWantToHit(Player player, BlackjackGame blackjackGame) {
        while (isAbleToAskHit(player, blackjackGame) &&
                Answer.of(InputView.inputDrawAnswer()).isYes()) {
            blackjackGame.hit(player);
            OutputView.printPlayerCards(player);
        }
    }

    private boolean isAbleToAskHit(Player player, BlackjackGame blackjackGame) {
        if (blackjackGame.isNotGameOver(player)) {
            OutputView.printAskOneMoreCard(player);
            return true;
        }
        return false;
    }

    private void proceedDealerRound(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        while (dealer.isMustHit()) {
            blackjackGame.hit(dealer);
            OutputView.printDealerDrawable(dealer);
        }
        OutputView.printDealerNotDrawable(dealer);
    }
}
