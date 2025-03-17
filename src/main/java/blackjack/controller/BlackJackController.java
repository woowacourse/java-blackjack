package blackjack.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import blackjack.model.card.Cards;
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
        players.getUsers().forEach(user -> betEachPlayer(user, blackJackGame));

        blackJackGame.dealInitialCards(players.getUsers());
        List<Cards> openCards = players.getUsers().stream()
                .map(blackJackGame::openInitialCards)
                .toList();
        outputView.printDealInitialCardsResult(players.getUsers(), openCards);

        usersDrawMoreCards(players.getUsers(), blackJackGame);

        dealerDrawMoreCards(blackJackGame, players.getUsers().getFirst());
        outputView.printOptimalPoints(blackJackGame.calculateOptimalPoints(players.getUsers()));
        Map<Player, Map<GameResult, Integer>> gameResults = blackJackGame.calculateResult(players);
        Map<Player, BigDecimal> playerWinnings = blackJackGame.calculatePlayerWinnings(gameResults);
        outputView.printFinalWinnings(playerWinnings);
    }

    private Players makePlayers() {
        List<Player> users = inputView.readUserNames()
                .stream()
                .map(userName -> (Player) new User(userName))
                .toList();
        return new Players(new Dealer(), users);
    }

    private void betEachPlayer(final Player player, final BlackJackGame blackJackGame) {
        if (player.isDealer()) {
            return;
        }
        int bettingAmount = inputView.readUserBettingAmount(player);
        blackJackGame.bet(player, bettingAmount);
    }

    private void usersDrawMoreCards(final List<Player> players, final BlackJackGame blackJackGame) {
        for (Player player : players) {
            userDrawMoreCards(blackJackGame, player);
        }
    }

    private void userDrawMoreCards(final BlackJackGame blackJackGame, final Player player) {
        if (player.isDealer()) {
            return;
        }
        while (blackJackGame.canDrawMoreCard(player) && inputView.readUserDrawMoreCard(player)) {
            blackJackGame.drawCard(player, 1);
            outputView.printPlayerCards(player, player.getCards());
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
