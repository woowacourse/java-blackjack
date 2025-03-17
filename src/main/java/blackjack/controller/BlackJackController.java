package blackjack.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import blackjack.model.card.initializer.DefaultCardDeckInitializer;
import blackjack.model.game.BlackJackGame;
import blackjack.model.game.GameResult;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.model.player.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJackGame blackJackGame = new BlackJackGame(new DefaultCardDeckInitializer());

        Players players = makePlayers();

        blackJackGame.drawInitialCards(players);
        outputView.printDealInitialCardsResult(players);

        usersDrawMoreCards(players.getUsers(), blackJackGame);

        dealerDrawMoreCards(blackJackGame, players.getDealer());
        outputView.printOptimalPoints(players);
        Map<Player, Map<GameResult, Integer>> gameResults = blackJackGame.calculateResult(players);
        Map<Player, BigDecimal> playerWinnings = players.calculateWinnings(gameResults);
        outputView.printFinalWinnings(playerWinnings);
    }

    private Players makePlayers() {
        Map<String, Integer> userNamesAndBettingAmount = inputView.readUserNamesAndBettingAmount();
        return new Players(new Dealer(), userNamesAndBettingAmount);
    }

    private void usersDrawMoreCards(final List<User> users, final BlackJackGame blackJackGame) {
        for (User user : users) {
            userDrawMoreCards(blackJackGame, user);
        }
    }

    private void userDrawMoreCards(final BlackJackGame blackJackGame, final Player player) {
        if (player.isDealer()) {
            return;
        }
        while (blackJackGame.canDrawMoreCard(player) && inputView.readUserDrawMoreCard(player)) {
            blackJackGame.drawCard(player, 1);
            outputView.printPlayerCards(player);
        }
    }

    private void dealerDrawMoreCards(final BlackJackGame blackJackGame, final Player dealer) {
        boolean isDealerDrawn = blackJackGame.canDrawMoreCard(dealer);
        if (isDealerDrawn) {
            blackJackGame.drawCard(dealer, 1);
        }
        outputView.printDealerDrawnMoreCards(isDealerDrawn);
    }

}
