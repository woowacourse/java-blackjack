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
        initHandsOf(deck, dealer, players);
        hitOrStand(deck, dealer, players);
        printResult(dealer, players);
    }

    private Players createPlayersWithInfo() {
        final List<String> names = InputView.receiveNames();
        final List<Money> playersMoney = InputView.receiveMoney(names);
        return Players.of(names, playersMoney);
    }

    private void initHandsOf(Deck deck, Dealer dealer, Players players) {
        StateInitializer.init2(deck, dealer, players);
        OutputView.printInitialCards2(dealer, players);
    }

    private void hitOrStand(Deck deck, Dealer dealer, Players players) {
        for (Player player : players) {
            hitOrStandForPlayer(deck, player);
        }
        hitOrStandForDealer(deck, dealer);
    }

    private void hitOrStandForPlayer(Deck deck, Player player) {
        while (player.canDraw2() && InputView.receiveAnswer(player.getName())) {
            player.receiveCard2(deck.pick());
            OutputView.printAllCards2(player);
        }
    }

    private void hitOrStandForDealer(Deck deck, Dealer dealer) {
        if (dealer.canDraw2()) {
            dealer.receiveCard2(deck.pick());
            OutputView.printDealerHitMessage();
        }
    }

    private void printResult(Dealer dealer, Players players) {
        OutputView.showAllCards2(dealer, players);
        OutputView.printResultTitle();
        OutputView.printProfit(ProfitCalculator.calculateProfitOf(dealer, players));
//        OutputView.printDealerResult(ResultMapper.resultOfDealer(dealer, players));
//        OutputView.printPlayersResult(ResultMapper.resultOfPlayers(dealer, players));
    }
}
