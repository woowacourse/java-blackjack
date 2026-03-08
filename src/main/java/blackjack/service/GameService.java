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
                player.get(Deck.draw());
            }
            dealer.get(Deck.draw());
        }
    }

}
