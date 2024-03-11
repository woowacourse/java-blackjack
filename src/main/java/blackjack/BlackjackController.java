package blackjack;

import blackjack.domain.cardgame.CardGame;
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
        final CardGame cardGame = new CardGame();
        final List<String> names = inputView.askPlayerNames();
        final Dealer dealer = new Dealer();
        final List<Player> players = names.stream().map(Player::new).toList();

        cardGame.initializeHand(dealer, players);
        outputView.printInitialHandOfEachPlayer(dealer, players);

        for (final Player player : players) {
            givePlayerMoreCardsIfWanted(cardGame, player);
        }
        giveDealerMoreCardsIfNeeded(cardGame, dealer);

        printHandStatusOfEachPlayer(dealer, players);
        printCardGameResult(dealer, players);
    }

    private void givePlayerMoreCardsIfWanted(final CardGame cardGame, final Player player) {
        final String playerName = player.getName();
        while (player.isAlive() && inputView.askForMoreCard(playerName)) {
            cardGame.giveCard(player);
            outputView.printPlayerCard(player);
        }
    }

    private void giveDealerMoreCardsIfNeeded(final CardGame cardGame, final Dealer dealer) {
        while (dealer.isMoreCardNeeded()) {
            cardGame.giveCard(dealer);
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
        final CardGameResult cardGameResult = dealer.judgeWithPlayers(players);
        outputView.printResult(cardGameResult);
    }
}
