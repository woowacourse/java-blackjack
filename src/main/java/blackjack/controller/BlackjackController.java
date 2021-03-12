package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.money.Money;
import blackjack.domain.utils.ProfitCalculator;
import blackjack.domain.utils.StateInitializer;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    public void run() {
        final Deck deck = Deck.create();
        final Dealer dealer = new Dealer();
        final Players players = createPlayersWithInfo();

        initStatesOf(deck, dealer, players);
        hitOrStand(deck, dealer, players);
        printResult(dealer, players);
    }

    private Players createPlayersWithInfo() {
        final List<String> names = InputView.receiveNames();
        final List<Money> playersMoney = InputView.receiveMoney(names);
        return Players.of(names, playersMoney);
    }

    private void initStatesOf(final Deck deck, final Dealer dealer, final Players players) {
        StateInitializer.init(deck, dealer, players);
        OutputView.printInitialCards(dealer, players);
    }

    private void hitOrStand(final Deck deck, final Dealer dealer, final Players players) {
        for (Player player : players) {
            hitOrStandForPlayer(deck, player);
        }
        hitOrStandForDealer(deck, dealer);
    }

    private void hitOrStandForPlayer(final Deck deck, final Player player) {
        while (player.canDraw() && InputView.receiveAnswer(player.getName())) {
            player.receiveCard(deck.pick());
            OutputView.printAllCards(player);
        }
        changeStateToFinished(player);
    }

    private void changeStateToFinished(Player player) {
        player.finish();
    }

    private void hitOrStandForDealer(final Deck deck, final Dealer dealer) {
        if (dealer.canDraw()) {
            dealer.receiveCard(deck.pick());
            OutputView.printDealerHitMessage();
        }
    }

    private void printResult(final Dealer dealer, final Players players) {
        OutputView.showAllCards(dealer, players);
        OutputView.printResultTitle();
        OutputView.printProfit(ProfitCalculator.calculateProfitOf(dealer, players));
    }
}
