package blackjack.service;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import java.util.List;

public class GameService {

    public void settingCards(List<Player> players, Dealer dealer) {
        Deck.shuffle();
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.bring(Deck.draw());
            }
            dealer.bring(Deck.draw());
        }
    }

    public void getMoreCard(Player player) {
        player.bring(Deck.draw());
    }

    public void getMoreCardForDealer(Dealer dealer) {
        dealer.bring(Deck.draw());
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
