package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame(setUpPlayers());
        distributeCards(blackjackGame);
        showUsersCards(blackjackGame);
        isPlayersHit(blackjackGame);
        isDealerHit(blackjackGame);
        showResults(blackjackGame);
    }

    private Players setUpPlayers() {
        OutputView.printInputNames();
        List<String> names = InputView.inputNames();
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            double money = InputView.inputBettingMoney(name);
            players.add(new Player(name, money));
        }
        return new Players(players);
    }

    private void distributeCards(BlackjackGame blackjackGame) {
        blackjackGame.distributeToUsers();
        OutputView.printDistribute(blackjackGame.getUsers());
    }

    private void showUsersCards(BlackjackGame blackjackGame) {
        OutputView.printCards(blackjackGame.getUsers());
    }

    private void isPlayersHit(BlackjackGame blackjackGame) {
        while (blackjackGame.isRunningForPlayers()) {
            Player currentPlayer = blackjackGame.currentPlayer();
            OutputView.printPlayerHit(currentPlayer);
            blackjackGame.drawCardToPlayer(InputView.inputHitAnswer());
            OutputView.printPlayerCards(currentPlayer);
        }
    }

    private void isDealerHit(BlackjackGame blackjackGame) {
        if (blackjackGame.isAbleToDrawCardToDealer()) {
            OutputView.printDealerHit();
            return;
        }
        OutputView.printDealerNotHit();
    }

    private void showResults(BlackjackGame blackjackGame) {
        OutputView.printResults(blackjackGame.getUsers());
        blackjackGame.calculateProfit();
        OutputView.printProfit(blackjackGame.getUsers(), blackjackGame.getProfit());
    }
}
