package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Deck;
import blackjack.domain.result.ResultBoard;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

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

    private List<String> setUpPlayers() {
        OutputView.printInputNames();
        return InputView.inputNames();
    }

    private void distributeCards(BlackjackGame blackjackGame) {
        blackjackGame.distributeToUsers();
        OutputView.printDistribute(blackjackGame.getUsers());
    }

    private void showUsersCards(BlackjackGame blackjackGame) {
        OutputView.printCards(blackjackGame.getUsers());
    }

    private void askToPlayersForHit(BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getPlayers()) {
            askToPlayerForHit(player, blackjackGame.getDeck());
        }
    }

    private void askToPlayerForHit(Player player, Deck deck) {
        while (isPlayerHit(player) && InputView.inputHitYes()) {
            player.draw(deck);
            OutputView.printPlayerCards(player);
        }
    }

    private boolean isPlayerHit(Player player) {
        if (player.isHit()) {
            OutputView.printPlayerHit(player);
            return true;
        }
        return false;
    }

    private void isDealerHit(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        if (dealer.isHit()) {
            dealer.draw(blackjackGame.getDeck());
            OutputView.printDealerHit();
            return;
        }
        OutputView.printDealerNotHit();
    }

    private void showResults(BlackjackGame blackjackGame) {
        OutputView.printResults(blackjackGame.getUsers());
        OutputView.printResultBoard(new ResultBoard(blackjackGame.getDealer(), blackjackGame.getPlayers()));
    }
}
