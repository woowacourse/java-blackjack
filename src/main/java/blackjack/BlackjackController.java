package blackjack;

import blackjack.domain.betting.BetDetails;
import blackjack.domain.betting.ProfitDetails;
import blackjack.domain.cardgame.CardDeck;
import blackjack.domain.cardgame.CardGameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    void run() {
        final List<Name> names = inputView.askPlayerNames();
        final Dealer dealer = new Dealer();
        final List<Player> players = names.stream().map(Player::new).toList();
        final CardDeck deck = CardDeck.createShuffledDeck();

        final BetDetails playersBetDetails = getPlayersBettingMoney(names);
        initializeHand(deck, dealer, players);
        outputView.printInitialHandOfEachPlayer(dealer, players);

        for (final Player player : players) {
            givePlayerMoreCardsIfWanted(deck, player);
        }
        giveDealerMoreCardsIfNeeded(deck, dealer);

        printHandStatusOfEachPlayer(dealer, players);
        final CardGameResult result = CardGameResult.of(dealer, players);
        ProfitDetails profits = playersBetDetails.calculateProfit(result);
        printProfit(profits);
    }

    private BetDetails getPlayersBettingMoney(final List<Name> playerNames) {
        return new BetDetails(playerNames.stream()
                .collect(
                        Collectors.toMap(
                                name -> name,
                                inputView::askBettingMoney,
                                (key, value) -> key,
                                LinkedHashMap::new
                        )));
    }

    private void initializeHand(final CardDeck deck, final Dealer dealer, final List<Player> players) {
        deck.giveInitialCards(dealer);
        for (final Player player : players) {
            deck.giveInitialCards(player);
        }
    }

    private void givePlayerMoreCardsIfWanted(final CardDeck deck, final Player player) {
        while (player.isAlive() && inputView.askForMoreCard(player.getName())) {
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

    private void printProfit(final ProfitDetails profits) {
        outputView.printPlayerProfit(profits);
    }
}
