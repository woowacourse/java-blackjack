package blackjack.controller;

import blackjack.domain.cardgame.BlackjackGame;
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
            final BlackjackGame blackjackGame = new BlackjackGame();
            final Dealer dealer = new Dealer();
            final List<String> names = InputView.readPlayerNames();
            final List<Player> players = readBettingAmountAndCreatePlayers(names);
            final Participants participants = new Participants(dealer, players);

            blackjackGame.initializeHand(dealer, players);
            OutputView.printInitialHandOfEachPlayer(dealer, players);

            givePlayersMoreCardsIfWanted(blackjackGame, players);
            giveDealerMoreCardsIfNeeded(blackjackGame, dealer);

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

    private void givePlayersMoreCardsIfWanted(final BlackjackGame blackjackGame, final List<Player> players) {
        for (final Player player : players) {
            givePlayerMoreCardsIfWanted(blackjackGame, player);
        }
    }

    private void givePlayerMoreCardsIfWanted(final BlackjackGame blackjackGame, final Player player) {
        final String playerName = player.getName();
        while (player.isDrawable() && InputView.readHitStandCommand(playerName)) {
            blackjackGame.giveCard(player);
            OutputView.printPlayerCard(player);
        }
    }

    private void giveDealerMoreCardsIfNeeded(final BlackjackGame blackjackGame, final Dealer dealer) {
        while (dealer.isDrawable()) {
            blackjackGame.giveCard(dealer);
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
