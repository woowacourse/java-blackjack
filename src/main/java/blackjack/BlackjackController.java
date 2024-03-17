package blackjack;

import blackjack.domain.betting.BetDetails;
import blackjack.domain.betting.ProfitDetails;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Participant;
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
        final List<Name> playerNames = inputView.askPlayerNames();
        final List<Player> players = createPlayers(playerNames);
        final BetDetails betting = readPlayersBettingMoney(playerNames);
        final Dealer dealer = new Dealer();
        final CardDeck deck = CardDeck.createShuffledDeck();

        initializeHand(deck, dealer, players);
        giveMoreCards(deck, players, dealer);
        printHandsOfEachParticipant(dealer, players);
        printCameResult(betting, dealer, players);
    }

    private List<Player> createPlayers(final List<Name> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private BetDetails readPlayersBettingMoney(final List<Name> playerNames) {
        return playerNames.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toMap(
                                        name -> name,
                                        inputView::askBettingMoney,
                                        (x, y) -> x,
                                        LinkedHashMap::new
                                ), BetDetails::new));
    }

    private void initializeHand(final CardDeck deck, final Dealer dealer, final List<Player> players) {
        deck.giveInitialCards(dealer);
        for (final Participant player : players) {
            deck.giveInitialCards(player);
        }
        outputView.printInitialHandOfEachPlayer(dealer, players);
    }

    private void giveMoreCards(final CardDeck deck, final List<Player> players, final Dealer dealer) {
        for (Player player : players) {
            givePlayerMoreCardsIfWanted(deck, player);
        }
        giveDealerMoreCardsIfNeeded(deck, dealer);
    }

    private void givePlayerMoreCardsIfWanted(final CardDeck deck, final Player player) {
        while (!player.isBusted() && inputView.askForMoreCard(player.name())) {
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

    private void printHandsOfEachParticipant(final Dealer dealer, final List<Player> players) {
        outputView.printParticipantCardWithScore(dealer);
        for (final Player player : players) {
            outputView.printParticipantCardWithScore(player);
        }
    }

    private void printCameResult(final BetDetails betting, final Dealer dealer, final List<Player> players) {
        ProfitDetails profits = betting.calculateProfit(dealer, players);
        outputView.printPlayerProfit(profits);
    }
}
