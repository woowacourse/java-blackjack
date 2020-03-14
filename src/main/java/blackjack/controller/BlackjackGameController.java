package blackjack.controller;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.user.*;
import blackjack.utils.InputHandler;
import blackjack.utils.ResultHandler;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGameController {
    public static void run() {
        Users users = enrollUsers();
        Deck deck = new Deck(CardFactory.getInstance().issueNewDeck());
        distributeInitialCards(users, deck);
        OutputView.printInitialCardDistribution(users);
        hitMoreCard(users, deck);
        decideDealerToHitCard(users, deck);
        OutputView.printFinalCardScore(users);
        OutputView.printFinalResult(ResultHandler.findAllWinners(users));
    }

    private static Users enrollUsers() {
        return UserFactory.generateUsers(
                InputHandler.parseName(InputView.inputPlayerName())
        );
    }

    private static void distributeInitialCards(Users users, Deck deck) {
        users.getUsers()
                .forEach(t -> t.receiveInitialCards(deck.drawInitialCards()));
    }

    private static void hitMoreCard(Users users, Deck deck) {
        users.getPlayer().forEach(user -> askForHit(deck, user));
    }

    private static void askForHit(Deck deck, User user) {
        while (InputView.askForHit(user.getName())) {
            user.receiveCard(deck.drawCard());
            OutputView.printCardStatus(user.showCardInfo());
            if (user.isBusted()) {
                OutputView.printBusted(user.getName());
                break;
            }
        }
    }

    private static void decideDealerToHitCard(Users users, Deck deck) {
        Dealer dealer = users.getDealer();
        while (dealer.isUnderThreshold()) {
            dealer.receiveCard(deck.drawCard());
            OutputView.printDealerHitMoreCard();
        }
    }
}
