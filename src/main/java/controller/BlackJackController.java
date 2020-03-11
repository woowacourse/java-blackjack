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

        Deck deck = CardFactory.create();
        deck.shuffle();

        dealer.receiveFirstCards(deck);
        players.receiveFirstCards(deck);
        OutputView.printFirstCardDealt(dealer, players);

        for (Player player : players.getPlayers()) {
            while (player.isSmallerThan(Cards.BLACKJACK_SCORE)
                && InputView.askWantMoreCard(player.getName())) {
                player.receiveCard(deck);
                OutputView.printPlayerCards(player);
            }
        }
    }
}
