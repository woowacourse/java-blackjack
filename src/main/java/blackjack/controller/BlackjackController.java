package blackjack.controller;

import blackjack.domain.cardgame.BettingAmount;
import blackjack.domain.cardgame.BlackjackGame;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {
    public void run() {
        try {
            final Dealer dealer = new Dealer();
            final List<Player> players = createPlayers();
            final BlackjackGame blackjackGame = new BlackjackGame();

            blackjackGame.initializeHand(dealer, players);
            OutputView.printInitialHandOfEachPlayer(dealer, players);

            bet(blackjackGame, players);

            givePlayersMoreCardsIfWanted(blackjackGame, players);
            giveDealerMoreCardsIfNeeded(blackjackGame, dealer);

            printHandStatusOfEachPlayer(dealer, players);
            printPlayerProfits(blackjackGame, dealer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Player> createPlayers() {
        final List<String> names = InputView.askPlayerNames();
        return names.stream()
                .map(Player::new)
                .toList();
    }

    private void givePlayersMoreCardsIfWanted(final BlackjackGame blackjackGame, final List<Player> players) {
        for (final Player player : players) {
            givePlayerMoreCardsIfWanted(blackjackGame, player);
        }
    }

    private void givePlayerMoreCardsIfWanted(final BlackjackGame blackjackGame, final Player player) {
        final String playerName = player.getName();
        while (!player.isBust() && InputView.askForMoreCard(playerName)) {
            blackjackGame.giveCard(player);
            OutputView.printPlayerCard(player);
        }
    }

    private void giveDealerMoreCardsIfNeeded(final BlackjackGame blackjackGame, final Dealer dealer) {
        while (dealer.isMoreCardNeeded()) {
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

    private static void bet(final BlackjackGame blackjackGame, final List<Player> players) {
        for (Player player : players) {
            final int bettingAmount = InputView.askBettingAmount(player.getName());
            blackjackGame.bet(player, new BettingAmount(bettingAmount));
        }
    }

    private void printPlayerProfits(final BlackjackGame blackjackGame, final Dealer dealer) {
        OutputView.printProfits(blackjackGame, dealer);
    }
}
