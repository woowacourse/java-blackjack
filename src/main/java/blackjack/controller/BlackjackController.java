package blackjack.controller;

import blackjack.domain.cardgame.CardGame;
import blackjack.domain.cardgame.CardGameJudge;
import blackjack.domain.cardgame.CardGameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {
    public void run() {
        final List<String> names = InputView.askPlayerNames();
        final List<Player> players = createPlayersByNames(names);
        final Dealer dealer = new Dealer();
        final CardGame cardGame = new CardGame();

        cardGame.initializeHand(dealer, players);
        OutputView.printInitialHandOfEachPlayer(dealer, players);

        givePlayersMoreCardsIfWanted(cardGame, players);
        giveDealerMoreCardsIfNeeded(cardGame, dealer);

        printHandStatusOfEachPlayer(dealer, players);
        printCardGameResult(dealer, players);
    }

    private static List<Player> createPlayersByNames(List<String> names) {
        return names.stream()
                .map(Player::new)
                .toList();
    }

    private void givePlayersMoreCardsIfWanted(final CardGame cardGame, final List<Player> players) {
        for (final Player player : players) {
            givePlayerMoreCardsIfWanted(cardGame, player);
        }
    }

    private void givePlayerMoreCardsIfWanted(final CardGame cardGame, final Player player) {
        final String playerName = player.getName();
        while (!player.isBust() && InputView.askForMoreCard(playerName)) {
            cardGame.giveCard(player);
            OutputView.printPlayerCard(player);
        }
    }

    private void giveDealerMoreCardsIfNeeded(final CardGame cardGame, final Dealer dealer) {
        while (dealer.isMoreCardNeeded()) {
            cardGame.giveCard(dealer);
            OutputView.printDealerHitMessage(dealer);
        }
    }

    private void printHandStatusOfEachPlayer(final Dealer dealer, final List<Player> players) {
        OutputView.printPlayerCardWithScore(dealer);
        for (final Player player : players) {
            OutputView.printPlayerCardWithScore(player);
        }
    }

    private void printCardGameResult(final Dealer dealer, final List<Player> players) {
        final CardGameJudge cardGameJudge = new CardGameJudge();
        final CardGameResult cardGameResult = cardGameJudge.judge(dealer, players);
        OutputView.printResult(cardGameResult);
    }
}
