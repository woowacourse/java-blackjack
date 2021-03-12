package blackjack.controller;

import blackjack.domain.BlackjackGame;
//import blackjack.domain.result.ResultBoard;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    public void run() {
        List<String> names = setUpNames();
        List<Double> moneyGroup = setUpMoneyGroup(names);
        BlackjackGame blackjackGame = new BlackjackGame(names, moneyGroup);
        distributeCards(blackjackGame);
        showUsersCards(blackjackGame);
        askToPlayersForHit(blackjackGame);
        isDealerHit(blackjackGame);
        showResults(blackjackGame);
    }

    private List<String> setUpNames() {
        OutputView.printInputNames();
        return InputView.inputNames();
    }

    private List<Double> setUpMoneyGroup(List<String> names) {
        return InputView.inputMoneyGroup(names);
    }

    private void distributeCards(BlackjackGame blackjackGame) {
        blackjackGame.distributeToUsers();
        OutputView.printDistribute(blackjackGame.getUsers());
    }

    private void showUsersCards(BlackjackGame blackjackGame) {
        OutputView.printCards(blackjackGame.getUsers());
    }

    private void askToPlayersForHit(BlackjackGame blackjackGame) {
        blackjackGame.getPlayers()
                .forEach(player -> askToPlayerForHit(blackjackGame, player));
    }

    private void askToPlayerForHit(BlackjackGame blackjackGame, Player player) {
        while (isPlayerHit(blackjackGame, player) && InputView.inputHitYes()) {
            blackjackGame.drawCardToPlayer(player);
            OutputView.printPlayerCards(player);
        }
    }

    private boolean isPlayerHit(BlackjackGame blackjackGame, Player player) {
        if (blackjackGame.isPlayerHit(player)) {
            OutputView.printPlayerHit(player);
            return true;
        }
        return false;
    }

    private void isDealerHit(BlackjackGame blackjackGame) {
        if (blackjackGame.drawCardToDealer()) {
            OutputView.printDealerHit();
            return;
        }
        OutputView.printDealerNotHit();
    }

    private void showResults(BlackjackGame blackjackGame) {
        OutputView.printResults(blackjackGame.getUsers());
        OutputView.printProfit(blackjackGame.getUsers());
//        OutputView.printResultBoard(new ResultBoard(blackjackGame.getDealer(), blackjackGame.getPlayers()));
    }
}
