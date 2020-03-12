package blackjack.controller;

import blackjack.domain.CardFactory;
import blackjack.domain.Deck;
import blackjack.domain.UserFactory;
import blackjack.domain.Users;
import blackjack.utils.DisplayHandler;
import blackjack.utils.NameParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGameController {
    public static void run() {
        Users users = UserFactory.generateUsers(
                NameParser.parse(InputView.inputPlayerName())
        );

        Deck deck = new Deck(CardFactory.getInstance().issueNewCards());
        users.getUsers()
                .forEach(t -> t.receiveInitialCards(deck.drawInitialCards()));
        OutputView.printInitialCardDistribution(DisplayHandler.parseInitialDistribution(users));
    }
}
