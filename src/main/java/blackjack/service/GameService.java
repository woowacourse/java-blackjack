package blackjack.service;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import java.util.List;

public class GameService {

    public void settingCards(List<Player> players, Dealer dealer) {
        dealer.shuffleCards();
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.bring(dealer.bringCard());
            }
            dealer.bring(dealer.bringCard());
        }
    }

    public void getMoreCard(Player player, Dealer dealer) {
        player.bring(dealer.bringCard());
    }

    public void getMoreCardForDealer(Dealer dealer) {
        dealer.bring(dealer.bringCard());
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
