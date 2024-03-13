package blackjack;

import blackjack.domain.cardgame.CardDeck;
import blackjack.domain.cardgame.CardGameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final List<String> names = inputView.askPlayerNames();
        final Dealer dealer = new Dealer();
        final List<Player> players = names.stream().map(Player::new).toList();
        final CardDeck deck = CardDeck.createShuffledDeck();

        initializeHand(deck, dealer, players);
        outputView.printInitialHandOfEachPlayer(dealer, players);

        for (final Player player : players) {
            givePlayerMoreCardsIfWanted(deck, player);
        }
        giveDealerMoreCardsIfNeeded(deck, dealer);

        printHandStatusOfEachPlayer(dealer, players);
        printCardGameResult(dealer, players);
    }

    private void initializeHand(final CardDeck deck, final Dealer dealer, final List<Player> players) {
        deck.giveInitialCards(dealer);
        for (final Player player : players) {
            deck.giveInitialCards(player);
        }
    }

    private void givePlayerMoreCardsIfWanted(final CardDeck deck, final Player player) {
        final String playerName = player.getName();
        while (player.isAlive() && inputView.askForMoreCard(playerName)) {
            deck.giveCard(player);
            outputView.printPlayerCard(player);
        }
    }

    private void giveDealerMoreCardsIfNeeded(final CardDeck deck, final Dealer dealer) {
        while (dealer.isMoreCardNeeded()) {
            deck.giveCard(dealer);
            outputView.printDealerHitMessage(dealer);
        }
    }

    private void printHandStatusOfEachPlayer(final Dealer dealer, final List<Player> players) {
        outputView.printPlayerCardWithScore(dealer);
        for (final Player player : players) {
            outputView.printPlayerCardWithScore(player);
        }
    }

    private void printCardGameResult(final Dealer dealer, final List<Player> players) {
        outputView.printResult(CardGameResult.of(dealer, players));
    }
}
