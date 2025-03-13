package blackjack.controller;

import blackjack.model.card.initializer.CardDeckInitializer;
import blackjack.model.game.Result;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    private final CardDeckInitializer cardDeckInitializer;

    public BlackJackController(
            final InputView inputView,
            final OutputView outputView,
            final CardDeckInitializer cardDeckInitializer
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardDeckInitializer = cardDeckInitializer;
    }

    public void run() {
        Dealer dealer = new Dealer(cardDeckInitializer);
        List<Player> players = makePlayers();

        dealInitialCards(dealer, players);
        drawMoreCards(dealer, players);
        printGameResults(dealer, players);

        players.forEach(dealer::fight);
        System.out.println("dealer = " + dealer.getProfit(players));
        players.forEach(player -> System.out.println("player.getProfit() = " + player.getProfit()));
    }

    private void dealInitialCards(final Dealer dealer, final List<Player> players) {
        dealer.dealInitialCards(players);
        outputView.printDealInitialCardsResult(dealer, players);
    }

    private void drawMoreCards(final Dealer dealer, final List<Player> players) {
        players.forEach(player -> drawMorePlayerCards(dealer, player));
        outputView.printDealerDrawnMoreCards(dealer.drawSelf());
    }

    private void printGameResults(final Dealer dealer, final List<Player> players) {
        outputView.printOptimalPoints(dealer, players);
        outputView.printGameResult(
                Result.evaluateDealerResults(dealer, players),
                getUsersResults(dealer, players)
        );
    }

    private List<Player> makePlayers() {
        return inputView.readUserNames()
                .stream()
                .map(name -> new Player(name, 1000))
                .toList();
    }

    private void drawMorePlayerCards(final Dealer dealer, final Player player) {
        while (dealer.canDrawMoreCard(player) && inputView.readUserDrawMoreCard(player)) {
            dealer.drawMoreCard(player);
            outputView.printPlayerCards(player);
        }
    }

    private Map<Player, Result> getUsersResults(final Dealer dealer, final List<Player> players) {
        return players.stream()
                .collect(
                        Collectors.toMap(
                                user -> user,
                                user -> Result.evaluateUserResult(dealer, user)
                        )
                );
    }
}
