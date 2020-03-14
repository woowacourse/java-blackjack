package blackjack.controller;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.user.*;
import blackjack.utils.InputHandler;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackGameController {
    private static final int INITIAL_CARDS = 2;

    public static void run() {
        Users users = enrollUsers();
        Deck deck = new Deck(CardFactory.getInstance().issueNewDeck());
        distributeInitialCards(users, deck);
        OutputView.printInitialCardDistribution(users);
        hitMoreCard(users.getPlayers(), deck);
        decideDealerToHitCard(users.getDealer(), deck);
        OutputView.printFinalCardScore(users);
        OutputView.printFinalResult(users.calculateAllResults());
    }

    private static Users enrollUsers() {
        return UserFactory.generateUsers(
                InputHandler.parseName(InputView.inputPlayerName())
        );
    }

    private static void distributeInitialCards(Users users, Deck deck) {
        users.getUsers()
                .forEach(t -> t.receiveInitialCards(deck.draw(INITIAL_CARDS)));
    }

    private static void hitMoreCard(List<Player> players, Deck deck) {
        players.forEach(user -> askForHit(deck, user));
    }

    private static void askForHit(Deck deck, User user) {
        while (InputView.askForHit(user.getName())) {
            user.receiveCard(deck.draw());
            OutputView.printCardStatus(user);
            if (user.isBusted()) {
                OutputView.printBusted(user.getName());
                break;
            }
        }
    }

    private static void decideDealerToHitCard(Dealer dealer, Deck deck) {
        while (dealer.isUnderThreshold()) {
            dealer.receiveCard(deck.draw());
            OutputView.printDealerHitMoreCard();
        }
    }
}
