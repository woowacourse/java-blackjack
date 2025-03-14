package blackjack.controller;

import java.util.ArrayList;
import java.util.List;

import blackjack.model.card.Cards;
import blackjack.model.card.initializer.DefaultCardDeckInitializer;
import blackjack.model.game.BlackJackGame;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
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

        List<Player> players = makePlayers();
        blackJackGame.dealInitialCards(players);
        List<Cards> openCards = players.stream()
                .map(blackJackGame::openInitialCards)
                .toList();
        outputView.printDealInitialCardsResult(players, openCards);

        usersDrawMoreCards(players, blackJackGame);

        dealerDrawMoreCards(blackJackGame, players.getFirst());
        outputView.printOptimalPoints(blackJackGame.calculateOptimalPoints(players));
        outputView.printGameResult(blackJackGame.calculateResult(players));
    }

    private List<Player> makePlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Dealer());
        for (String username : inputView.readUserNames()) {
            players.add(new User(username));
        }
        return players;
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
            outputView.printPlayerCards(player, player.getCards());
        }
    }

    private void dealerDrawMoreCards(final BlackJackGame blackJackGame, final Player dealer) {
        boolean isDrawn = blackJackGame.canDrawMoreCard(dealer);
        outputView.printDealerDrawnMoreCards(isDrawn);
    }

}
