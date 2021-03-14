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
        askToPlayersForHit(blackjackGame);
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
        blackjackGame.calculateProfit();
        OutputView.printProfit(blackjackGame.getUsers(), blackjackGame.getProfit());
    }
}
