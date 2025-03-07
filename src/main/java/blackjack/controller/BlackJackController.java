package blackjack.controller;

import blackjack.model.card.CardDeck;
import blackjack.model.card.initializer.DefaultCardDeckInitializer;
import blackjack.model.game.BlackJackGame;
import blackjack.model.game.Rule;
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

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardDeck cardDeck = CardDeck.initializeFrom(new DefaultCardDeckInitializer());
        Rule rule = new Rule();
        BlackJackGame blackJackGame = new BlackJackGame(cardDeck, rule);

        Dealer dealer = new Dealer("딜러");
        List<User> users = new ArrayList<>();
        for (String userName : inputView.readUserNames()) {
            users.add(new User(userName));
        }
        dealInitialCards(users, dealer, blackJackGame);
        outputView.printDealInitialCardsResult(dealer, users, rule);

        for (User user : users) {
            while (rule.canDrawMoreCard(user) && inputView.readUserDrawMoreCard(user)) {
                blackJackGame.drawMoreCard(user);
                outputView.printPlayerCards(user);
            }
        }

        outputView.printDealerDrawnMoreCards(blackJackGame.drawMoreCard(dealer));
        outputView.printOptimalPoints(dealer, users, rule);
        outputView.printGameResult(rule.calculateResult(dealer, users));
    }

    private void dealInitialCards(final List<User> users, final Player dealer, final BlackJackGame blackJackGame) {
        List<Player> allPlayers = new ArrayList<>(users);
        allPlayers.add(dealer);
        blackJackGame.dealInitialCards(allPlayers);
    }

}
