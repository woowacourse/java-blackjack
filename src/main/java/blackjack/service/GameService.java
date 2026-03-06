package blackjack.service;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import java.util.List;

public class GameService {

    private final Deck deck;

    public GameService(Deck deck) {
        this.deck = deck;
    }

    public void settingCards(List<Player> players, Dealer dealer) {
        deck.shuffle();
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.bring(deck.bringTopCard());
            }
            dealer.bring(deck.bringTopCard());
        }
    }

    public void getMoreCard(Player player, Dealer dealer) {
        player.bring(deck.bringTopCard());
    }

    public void getMoreCardForDealer(Dealer dealer) {
        dealer.bring(deck.bringTopCard());
    }

    public boolean isDealerWinning(Player player, Dealer dealer) {
        if (player.isBurst()) {
            return true;
        }
        if (dealer.isBurst()) {
            return false;
        }

        return player.calculateCardsValue() < dealer.calculateCardsValue();
    }

}
