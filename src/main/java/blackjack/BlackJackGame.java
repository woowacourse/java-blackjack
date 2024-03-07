package blackjack;

import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.Player;
import blackjack.model.Players;
import blackjack.model.Referee;
import blackjack.model.Rule;
import blackjack.util.ConsoleReader;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {
    private static final ConsoleReader CONSOLE_READER = new ConsoleReader();

    public void run() {
        final Deck deck = new Deck();

        final List<String> names = InputView.readPlayerNames(CONSOLE_READER);
        final Players players = Players.from(names, deck.distributeInitialCard(names.size()));

        final Dealer dealer = new Dealer(deck.distributeInitialCard());
        final Referee referee = new Referee(new Rule(dealer), players);

        OutputView.printDistributionSubject(players.getNames());
        printInitialCards(dealer, players);

        playPlayersTurn(players.getPlayers(), deck);
        playDealerTurn(dealer, deck);
    }

    private void printInitialCards(final Dealer dealer, final Players players) {
        OutputView.printInitialCards(dealer.getName(), dealer.openCard());
        players.collectCardsOfEachPlayer()
                .forEach(OutputView::printInitialCards);
    }

    private void playPlayersTurn(final List<Player> players, final Deck deck) {
        for (Player player : players) {
            playPlayerTurn(player, deck);
        }
    }

    private void playPlayerTurn(final Player player, final Deck deck) {
        if (!player.isBust()) {
            final boolean isHit = InputView.readHitOrNot(player.getName(), CONSOLE_READER);
            distributeIfPlayerWant(isHit, player, deck);
        }
    }

    private void distributeIfPlayerWant(final boolean isHit, final Player player, final Deck deck) {
        if (isHit) {
            distributeNewCard(player, deck);
            System.out.println(player.openCards());
            playPlayerTurn(player, deck);
        }
    }

    private void playDealerTurn(final Dealer dealer, final Deck deck) {
        while (dealer.canHit()) {
            OutputView.printDealerHit();
            distributeNewCard(dealer, deck);
        }
    }

    private void distributeNewCard(final Player player, final Deck deck) {
        player.receiveCard(deck.distribute());
    }
}
