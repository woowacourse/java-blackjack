package controller;

import domain.card.CardFactory;
import domain.card.Cards;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    public static void run() {
        Players players = new Players(InputView.readPlayerNames());
        Dealer dealer = new Dealer();
        Deck deck = createShuffledDeck();

        doFirstDeal(players, dealer, deck);

        dealToPlayers(players, deck);
        dealToDealer(dealer, deck);

        OutputView.printFinalCardStatus(dealer, players);
    }

    private static void dealToDealer(Dealer dealer, Deck deck) {
        while (!dealer.isLargerThan(Cards.MAX_SUM_FOR_DEALER_MORE_CARD)) {
            dealer.receiveCard(deck);
            OutputView.printDealerHasReceivedMoreCard();
        }
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
        return player.isSmallerThan(Cards.BLACKJACK_SCORE)
            && InputView.askWantMoreCard(player.getName());
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
}
