package blackjack.controller;

import blackjack.model.card.initializer.CardDeckInitializer;
import blackjack.model.game.BlackJackGame;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CardDeckInitializer cardDeckInitializer;

    public BlackJackController(
            final InputView inputView, final OutputView outputView, final CardDeckInitializer cardDeckInitializer
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardDeckInitializer = cardDeckInitializer;
    }

    public void run() {
        BlackJackGame blackJackGame = new BlackJackGame(cardDeckInitializer);

        Dealer dealer = new Dealer("딜러");
        List<User> users = makeUsers();
        dealInitialCards(users, dealer, blackJackGame);
        outputView.printDealInitialCardsResult(dealer, users);

        usersDrawMoreCards(users, blackJackGame);

        dealerDrawMoreCards(blackJackGame, dealer);
        outputView.printOptimalPoints(dealer, users);
        outputView.printGameResult(blackJackGame.calculateResult(dealer, users));
    }

    private List<User> makeUsers() {
        return inputView.readUserNames()
                .stream()
                .map(User::new)
                .toList();
    }

    private void usersDrawMoreCards(final List<User> users, final BlackJackGame blackJackGame) {
        for (User user : users) {
            userDrawMoreCards(blackJackGame, user);
        }
    }

    private void userDrawMoreCards(final BlackJackGame blackJackGame, final User user) {
        while (user.canDrawMoreCard() && inputView.readUserDrawMoreCard(user)) {
            blackJackGame.drawMoreCard(user);
            outputView.printPlayerCards(user);
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
