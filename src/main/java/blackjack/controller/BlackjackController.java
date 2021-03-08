package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Deck;
import blackjack.domain.result.ResultBoard;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    private static final String YES = "y";

    private final BlackjackGame blackjackGame;

    public BlackjackController(BlackjackGame blackjackGame) {
        this.blackjackGame = blackjackGame;
    }

    public void run() {
        setUpBlackjackGame();
        distributeCards();
        showUsersCards();
        askToPlayersForHit();
        isDealerHit();
        showResults();
    }

    private void setUpBlackjackGame() {
        blackjackGame.setUpPlayers(setUpPlayers());
        blackjackGame.setUpUsers();
    }

    private List<String> setUpPlayers() {
        OutputView.printInputNames();
        return InputView.inputNames();
    }

    private void distributeCards() {
        blackjackGame.distributeToUsers();
        OutputView.printDistribute(blackjackGame.getDealer(), blackjackGame.getPlayers());
    }

    private void showUsersCards() {
        OutputView.printDealerCard(blackjackGame.getDealer());
        OutputView.printPlayersCards(blackjackGame.getPlayers());
    }

    private void askToPlayersForHit() {
        Players players = blackjackGame.getPlayers();
        for (Player player : players.getPlayers()) {
            askToPlayerForHit(player, blackjackGame.getDeck());
        }
    }

    private void askToPlayerForHit(Player player, Deck deck) {
        while (isPlayerHit(player) && isYes(InputView.inputHit())) {
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

    private boolean isYes(String answer) {
        return answer.equals(YES);
    }

    private void isDealerHit() {
        Dealer dealer = blackjackGame.getDealer();
        if (dealer.isHit()) {
            dealer.draw(blackjackGame.getDeck());
            OutputView.printDealerHit(dealer);
            return;
        }
        OutputView.printDealerNotHit(dealer);
    }

    private void showResults() {
        OutputView.printResults(blackjackGame.getUsers());
        OutputView.printResultBoard(blackjackGame.getDealer(),
                new ResultBoard(blackjackGame.getDealer(), blackjackGame.getPlayers()));
    }
}
