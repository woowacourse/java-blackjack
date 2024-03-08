package blackjack;

import blackjack.domain.cardgame.CardGame;
import blackjack.domain.cardgame.CardGameJudge;
import blackjack.domain.cardgame.CardGameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    public static void main(String[] args) {
        final CardGame cardGame = new CardGame();
        final List<String> names = InputView.askPlayerNames();
        final Dealer dealer = new Dealer();
        final List<Player> players = names.stream()
                .map(Player::new)
                .toList();

        cardGame.initializeHand(dealer, players);
        OutputView.printInitialHandOfEachPlayer(dealer, players);

        for (final Player player : players) {
            givePlayerMoreCardsIfWanted(cardGame, player);
        }
        giveDealerMoreCardsIfNeeded(cardGame, dealer);

        printHandStatusOfEachPlayer(dealer, players);
        printCardGameResult(dealer, players);
    }

    private static void givePlayerMoreCardsIfWanted(final CardGame cardGame, final Player player) {
        final String playerName = player.getName();
        while (player.isAlive() && InputView.askForMoreCard(playerName)) {
            cardGame.giveCard(player);
            OutputView.printPlayerCard(player);
        }
    }

    private static void giveDealerMoreCardsIfNeeded(final CardGame cardGame, final Dealer dealer) {
        while (dealer.isMoreCardNeeded()) {
            cardGame.giveCard(dealer);
            OutputView.printDealerHitMessage(dealer);
        }
    }

    private static void printHandStatusOfEachPlayer(final Dealer dealer, final List<Player> players) {
        OutputView.printPlayerCardWithScore(dealer);
        for (final Player player : players) {
            OutputView.printPlayerCardWithScore(player);
        }
    }

    private static void printCardGameResult(final Dealer dealer, final List<Player> players) {
        final CardGameJudge cardGameJudge = new CardGameJudge();
        final CardGameResult cardGameResult = cardGameJudge.judge(dealer, players);
        OutputView.printResult(cardGameResult);
    }
}
