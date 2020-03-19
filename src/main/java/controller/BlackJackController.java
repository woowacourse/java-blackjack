package controller;

import domain.card.CardFactory;
import domain.card.Deck;
import domain.result.ResultCalculator;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import view.InputView;
import view.OutputView;

import java.util.Map;

public class BlackJackController {
    public static void run() {
        try {
            runWithoutExceptionCatch();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
        }
    }

    private static void runWithoutExceptionCatch() {
        Players players = new Players(InputView.readPlayerNames());
        Dealer dealer = new Dealer();
        Deck deck = createShuffledDeck();

        doFirstDeal(players, dealer, deck);
        dealToPlayers(players, deck);
        dealToDealer(dealer, deck);

        OutputView.printFinalCardStatus(dealer, players);
        conclude(dealer, players);
    }

    private static Deck createShuffledDeck() {
        Deck deck = CardFactory.create();
        deck.shuffle();
        return deck;
    }

    private static void doFirstDeal(Players players, Dealer dealer, Deck deck) {
        dealer.receiveFirstCards(deck);
        players.receiveFirstCards(deck);
        OutputView.printFirstCardDealt(dealer, players);
    }

    private static void dealToPlayers(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            dealToPlayer(deck, player);
        }
    }

    private static void dealToPlayer(Deck deck, Player player) {
        while (willPlayerGetMoreCard(player)) {
            player.receiveCard(deck);
            OutputView.printPlayerCards(player);
        }
    }

    private static boolean willPlayerGetMoreCard(Player player) {
        return player.isReceiveAble()
                && InputView.askWantMoreCard(player.getName());
    }

    private static void dealToDealer(Dealer dealer, Deck deck) {
        while (dealer.isReceiveAble()) {
            dealer.receiveCard(deck);
            OutputView.printDealerHasReceivedMoreCard();
        }
    }

    private static void conclude(Dealer dealer, Players players) {
        ResultCalculator resultCalculator = new ResultCalculator();
        resultCalculator.calculateDealerAndPlayersResult(dealer, players);
        OutputView.printResultMessage();
        OutputView.printDealerResult(resultCalculator.getDealerResult());
        for (Map.Entry<String, Double> playerResult : resultCalculator.getPlayersResult().entrySet()) {
            OutputView.printPlayerResult(playerResult);
        }
    }
}
