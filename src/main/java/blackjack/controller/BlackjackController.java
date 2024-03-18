package blackjack.controller;

import blackjack.domain.cardgame.CardDeck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    public void run() {
        try {
            final CardDeck cardDeck = new CardDeck();
            final Dealer dealer = new Dealer();
            final List<String> names = InputView.readPlayerNames();
            final List<Player> players = readBettingAmountAndCreatePlayers(names);
            final Participants participants = new Participants(dealer, players);

            cardDeck.initializeHand(participants);
            OutputView.printInitialHandOfEachPlayer(dealer, players);

            givePlayersMoreCardsIfWanted(cardDeck, players);
            giveDealerMoreCardsIfNeeded(cardDeck, dealer);

            printHandStatusOfEachPlayer(dealer, players);
            printPlayerProfits(participants);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Player> readBettingAmountAndCreatePlayers(final List<String> names) {
        final List<Player> players = new ArrayList<>();
        for (final String name : names) {
            final int bettingAmount = InputView.readBettingAmount(name);
            players.add(new Player(name, bettingAmount));
        }
        return players;
    }

    private void givePlayersMoreCardsIfWanted(final CardDeck cardDeck, final List<Player> players) {
        for (final Player player : players) {
            givePlayerMoreCardsIfWanted(cardDeck, player);
        }
    }

    private void givePlayerMoreCardsIfWanted(final CardDeck cardDeck, final Player player) {
        final String playerName = player.getName();
        while (player.isDrawable() && InputView.readHitStandCommand(playerName)) {
            cardDeck.giveCard(player);
            OutputView.printPlayerCard(player);
        }
    }

    private void giveDealerMoreCardsIfNeeded(final CardDeck cardDeck, final Dealer dealer) {
        while (dealer.isDrawable()) {
            cardDeck.giveCard(dealer);
            OutputView.printDealerHitMessage(dealer);
        }
    }

    private void printHandStatusOfEachPlayer(final Dealer dealer, final List<Player> players) {
        OutputView.printPlayerCardWithScore(dealer);
        for (final Player player : players) {
            OutputView.printPlayerCardWithScore(player);
        }
    }

    private void printPlayerProfits(final Participants participants) {
        OutputView.printProfits(participants);
    }
}
