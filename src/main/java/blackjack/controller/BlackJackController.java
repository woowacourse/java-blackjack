package blackjack.controller;

import java.util.ArrayList;
import java.util.List;

import blackjack.model.card.initializer.DefaultCardDeckInitializer;
import blackjack.model.game.BlackJackGame;
import blackjack.model.game.Rule;
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
        Rule rule = new Rule();
        BlackJackGame blackJackGame = new BlackJackGame(new DefaultCardDeckInitializer(), rule);

        Dealer dealer = new Dealer();
        List<User> users = makeUsers();
        dealInitialCards(users, dealer, blackJackGame);
        outputView.printDealInitialCardsResult(dealer, users, rule);

        usersDrawMoreCards(users, rule, blackJackGame);

        dealerDrawMoreCards(blackJackGame, dealer);
        outputView.printOptimalPoints(dealer, users, rule);
        outputView.printGameResult(rule.calculateResult(dealer, users));
    }

    private List<User> makeUsers() {
        return inputView.readUserNames()
                .stream()
                .map(User::new)
                .toList();
    }

    private void usersDrawMoreCards(final List<User> users, final Rule rule, final BlackJackGame blackJackGame) {
        for (User user : users) {
            userDrawMoreCards(rule, blackJackGame, user);
        }
    }

    private void userDrawMoreCards(final Rule rule, final BlackJackGame blackJackGame, final User user) {
        while (rule.canDrawMoreCard(user) && inputView.readUserDrawMoreCard(user)) {
            blackJackGame.drawMoreCard(user);
            outputView.printPlayerCards(user, rule);
        }
    }

    private void dealerDrawMoreCards(final BlackJackGame blackJackGame, final Dealer dealer) {
        boolean isDrawn = blackJackGame.drawMoreCard(dealer);
        outputView.printDealerDrawnMoreCards(isDrawn);
    }

    private void dealInitialCards(final List<User> users, final Player dealer, final BlackJackGame blackJackGame) {
        List<Player> allPlayers = new ArrayList<>(users);
        allPlayers.add(dealer);
        blackJackGame.dealInitialCards(allPlayers);
    }

}
