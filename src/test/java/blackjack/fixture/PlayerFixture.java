package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

public class PlayerFixture {
    public static Player player(int bettingAmount, Card... cards) {
        Player player = new Player("player", 10000);
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
