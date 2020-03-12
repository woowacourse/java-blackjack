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
        OutputView.printFinalCardScore(DisplayHandler.parseFinalScoreAnnouncement(users));
        OutputView.printFinalResult(ResultHandler.findAllWinners(users));
    }
}
