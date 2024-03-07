package blackjack;

import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.Players;
import blackjack.model.Referee;
import blackjack.model.Rule;
import blackjack.util.ConsoleReader;
import blackjack.view.InputView;
import java.util.List;

public class BlackJackGame {
    private static final ConsoleReader CONSOLE_READER = new ConsoleReader();

    public void run() {
        final Deck deck = new Deck();

        final List<String> names = InputView.readPlayerNames(CONSOLE_READER);
        final Players players = Players.from(names, deck.distributeInitialCard(names.size()));

        final Dealer dealer = new Dealer(deck.distributeInitialCard());
        final Referee referee = new Referee(new Rule(dealer), players);
    }
}
