package blackjack.controller;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.UserFactory;
import blackjack.domain.user.Users;
import blackjack.utils.InputHandler;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGameController {
    public static void run() {
        Users users = enrollUsers();
        Deck deck = new Deck(CardFactory.getInstance().issueNewCards());
        BlackjackGame blackjackGame = new BlackjackGame(users, deck);
        blackjackGame.distributeInitialCards();
        OutputView.printInitialCardDistribution(users);
        InputHandler.hitMoreCard(users, deck);
        decideDealerToHitCard(users, deck);
        OutputView.printFinalCardScore(users);
        OutputView.printFinalResult(blackjackGame.calculateAllResult(users));
    }

    private static Users enrollUsers() {
        return UserFactory.generateUsers(
                InputHandler.parseName(InputView.inputPlayerName())
        );
    }

    private static void decideDealerToHitCard(Users users, Deck deck) {
        Dealer dealer = users.getDealer();
        if (dealer.isUnderThreshold()) {
            dealer.receiveCard(deck.draw());
            OutputView.printDealerHitMoreCard();
        }
    }
}
