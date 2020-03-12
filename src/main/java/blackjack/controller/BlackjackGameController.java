package blackjack.controller;

import blackjack.domain.*;
import blackjack.utils.DisplayHandler;
import blackjack.utils.InputHandler;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGameController {
    public static void run() {
        Users users = UserFactory.generateUsers(
                InputHandler.parseName(InputView.inputPlayerName())
        );

        Deck deck = new Deck(CardFactory.getInstance().issueNewCards());
        users.getUsers()
                .forEach(t -> t.receiveInitialCards(deck.drawInitialCards()));
        OutputView.printInitialCardDistribution(DisplayHandler.parseInitialDistribution(users));
        InputHandler.hitMoreCard(users, deck);
        Dealer dealer = users.getDealer();
        if (dealer.isUnderThreshold()) {
            dealer.receiveCard(deck.drawCard());
            OutputView.printDealerHitMoreCard();
        }
        OutputView.printFinalResult(DisplayHandler.parseFinalResult(users));
    }
}
