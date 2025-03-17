package blackjack.controller;

import blackjack.model.blackjack_player.dealer.Dealer;
import blackjack.model.blackjack_player.dealer.result.Result;
import blackjack.model.blackjack_player.player.Player;
import blackjack.model.card.initializer.CardDeckInitializer;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public final class BlackJackController {

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
        fight(dealer, players);
        printGameResults(dealer, players);
    }

    private List<Player> makePlayers() {
        return inputView.readUserNames()
                .stream()
                .map(name -> new Player(name, inputView.readBettingMoney(name)))
                .toList();
    }

    private void dealInitialCards(final Dealer dealer, final List<Player> players) {
        dealer.dealStartingHand();
        players.forEach(player -> player.receiveCards(dealer.drawPlayerStartingCards()));
        outputView.printDealInitialCardsResult(dealer, players);
    }

    private void drawMoreCards(final Dealer dealer, final List<Player> players) {
        players.forEach(player -> drawMorePlayerCards(dealer, player));
        outputView.printDealerDrawnMoreCards(dealer.dealSelf());
    }

    private void fight(final Dealer dealer, final List<Player> players) {
        for (Player player : players) {
            player.applyResult(Result.calculate(dealer, player));
        }
    }

    private void drawMorePlayerCards(final Dealer dealer, final Player player) {
        while (player.canReceiveMoreCard() && inputView.readUserDrawMoreCard(player)) {
            player.receiveCards(dealer.drawPlayerCards());
            outputView.printPlayerCards(player);
        }
    }

    private void printGameResults(final Dealer dealer, final List<Player> players) {
        outputView.printOptimalPoints(dealer, players);
        outputView.printGameResult(players);
    }
}
