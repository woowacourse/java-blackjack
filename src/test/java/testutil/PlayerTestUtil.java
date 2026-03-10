package testutil;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;

import java.util.List;

public final class PlayerTestUtil {
    public static Player createPlayer(List<Card> cards) {
        Player player = new Player("AAAA");
        cards.forEach(player::add);
        return player;
    }

    public static Dealer createDealer(List<Card> cards) {
        Dealer dealer = new Dealer("AAAA");
        cards.forEach(dealer::add);
        return dealer;
    }
}
