package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.GameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {

    private CardDeck cardDeck;

    public GameController(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public void run() {
        List<String> usersName = InputView.inputUsers();
        Player dealer = new Dealer(cardDeck.drawInitialCard());
        List<Player> users = usersName.stream()
                .map(user -> new User(user, cardDeck.drawInitialCard()))
                .collect(Collectors.toList());
        ResultView.printDealerCard(dealer);
        ResultView.printUsersCards(users);
        askReceiveCardToUsers(users);
        if (dealer.isPossibleToPickCard()) {
            dealer.pickCard(cardDeck.drawCard());
            ResultView.printDealerReceiveCard();
        }
        ResultView.printTotalCardResult(dealer, users);

        GameResult gameResult = GameResult.createPlayerGameResult(dealer, users);
    }

    private void askReceiveCardToUsers(List<Player> players) {
        for (Player user : players) {
            askReceiveCard(user);
        }
    }

    private void askReceiveCard(Player user) {
        if (user.isPossibleToPickCard()) {
            Boolean pick = InputView.inputDrawCardAnswer(user);
            drawCard(user, pick);
        }
    }

    private void drawCard(Player user, Boolean pick) {
        if (pick) {
            user.pickCard(cardDeck.drawCard());
            ResultView.printUserCards(user);
            askReceiveCard(user);
        } else if (!pick) {
            ResultView.printUserCards(user);
        }
    }
}
