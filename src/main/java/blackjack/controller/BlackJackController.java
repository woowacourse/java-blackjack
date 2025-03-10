package blackjack.controller;

import blackjack.model.game.BlackJackGame;
import blackjack.model.game.Result;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    private final BlackJackGame blackJackGame;

    public BlackJackController(
            final InputView inputView,
            final OutputView outputView,
            final BlackJackGame blackJackGame
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJackGame = blackJackGame;
    }

    public void run() {
        Dealer dealer = new Dealer();
        List<User> users = makeUsers();
        dealInitialCards(users, dealer);
        outputView.printDealInitialCardsResult(dealer, users);

        users.forEach(this::userDrawMoreCards);
        outputView.printDealerDrawnMoreCards(blackJackGame.drawMoreCard(dealer));

        outputView.printOptimalPoints(dealer, users);

        outputView.printGameResult(
                Result.evaluateDealerResults(dealer, users),
                getUsersResults(dealer, users)
        );
    }

    private List<User> makeUsers() {
        return inputView.readUserNames()
                .stream()
                .map(User::new)
                .toList();
    }

    private void userDrawMoreCards(final User user) {
        while (user.canDrawMoreCard() && inputView.readUserDrawMoreCard(user)) {
            blackJackGame.drawMoreCard(user);
            outputView.printPlayerCards(user);
        }
    }

    private void dealInitialCards(final List<User> users, final Player dealer) {
        List<Player> allPlayers = new ArrayList<>(users);
        allPlayers.add(dealer);
        blackJackGame.dealInitialCards(allPlayers);
    }

    private Map<User, Result> getUsersResults(final Dealer dealer, final List<User> users) {
        return users.stream()
                .collect(
                        Collectors.toMap(
                                user -> user,
                                user -> Result.evaluateUserResult(dealer, user)
                        )
                );
    }
}
