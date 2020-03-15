package blackjack.controller;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.UserFactory;
import blackjack.domain.user.Users;
import blackjack.utils.DisplayHandler;
import blackjack.utils.InputHandler;
import blackjack.utils.ResultHandler;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGameController {
    public static void run() {
        Users users = enrollUsers();
        Deck deck = new Deck(CardFactory.getInstance().issueNewCards());
        distributeInitialCards(users, deck);
        OutputView.printInitialCardDistribution(DisplayHandler.parseInitialDistribution(users));
        InputHandler.hitMoreCard(users, deck);
        decideDealerToHitCard(users, deck);
        OutputView.printFinalCardScore(DisplayHandler.parseFinalScoreAnnouncement(users));
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

    private static void decideDealerToHitCard(Users users, Deck deck) {
        Dealer dealer = users.getDealer();
        if (dealer.isUnderThreshold()) {
            dealer.receiveCard(deck.draw());
            OutputView.printDealerHitMoreCard();
        }
    }
}
