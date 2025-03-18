package blackjack.controller;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.factory.DealerFactory;
import blackjack.factory.PlayersFactory;
import blackjack.factory.SingleDeckFactory;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BlackjackController {

    public void run() {
        List<String> names = InputView.readNames();
        List<Integer> bettingMoneyList = InputView.readBettingMoneyList(names);
        Deck deck = SingleDeckFactory.generate();
        Players players = PlayersFactory.generate(names, bettingMoneyList);
        Dealer dealer = DealerFactory.generate();

        takeCards(deck, players, dealer);

        players.adjustBalance(dealer);
        printResult(players, dealer);
    }

    private void takeCards(Deck deck, Players players, Dealer dealer) {
        dealInitialCards(deck, players, dealer);

        hitCard(deck, players);
        hitCard(deck, dealer);
    }

    private void dealInitialCards(Deck deck, Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            takeCards(Deck::startingHand, deck, player);
        }

        takeCards(Deck::startingHand, deck, dealer);

        OutputView.printHand(dealer, players);
    }

    private void takeCards(Function<Deck, List<Card>> function, Deck deck, Participant participant) {
        List<Card> cards = function.apply(deck);

        cards.forEach(participant::takeCard);
    }

    private void hitCard(Deck deck, Players players) {
        for (Player player : players.getPlayers()) {
            hitCard(deck, player);
        }
    }

    private void hitCard(Deck deck, Player player) {
        if (player.isBlackjack()) {
            return;
        }

        while (canDraw(player)) {
            takeCards(Deck::hit, deck, player);
            OutputView.printHand(player);
        }
    }

    private boolean canDraw(Player player) {
        return isNotBusted(player) && player.canHit() && InputView.askToGetMoreCard(player) != Confirmation.N;
    }

    private boolean isNotBusted(Player player) {
        boolean busted = player.isBusted();
        if (busted) {
            OutputView.printBustedPlayer(player);
        }

        return !busted;
    }

    private void hitCard(Deck deck, Dealer dealer) {
        while (dealer.canHit()) {
            OutputView.printMoreCard();
            takeCards(Deck::hit, deck, dealer);
        }
    }

    private void printResult(Players players, Dealer dealer) {
        int playersTotalRevenue = players.getTotalRevenue();
        Map<Player, Integer> revenueMap = players.getRevenueMap();

        OutputView.printCardResult(players, dealer);
        OutputView.printRevenue(playersTotalRevenue, revenueMap);
    }
}
