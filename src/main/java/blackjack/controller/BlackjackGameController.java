package blackjack.controller;

import blackjack.domain.CardFactory;
import blackjack.domain.Deck;
import blackjack.domain.UserFactory;
import blackjack.domain.Users;
import blackjack.utils.NameParser;
import blackjack.view.InputView;

public class BlackjackGameController {
    public static void run() {
        Users users = UserFactory.generateUsers(
                NameParser.parse(InputView.inputPlayerName())
        );

        Deck deck = new Deck(CardFactory.getInstance().issueNewCards());


    }
}
