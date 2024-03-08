package blackjack.fixture;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;

public class PlayerFixture {
    public static Player player(Card... cards) {
        Player player = new Player("player");
        for (Card card : cards) {
            player.addCard(card);
        }
        return player;
    }

    public static Dealer dealer(Card... cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.addCard(card);
        }
        return dealer;
    }
}
