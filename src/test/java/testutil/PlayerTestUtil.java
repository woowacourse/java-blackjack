package testutil;

import domain.card.Card;
import domain.participant.Bet;
import domain.participant.Dealer;
import domain.participant.Player;

import java.util.List;

public final class PlayerTestUtil {
    public static final long DEFAULT_BET_AMOUNT = 2_000_000;

    public static Player createPlayer(List<Card> cards) {
        Player player = new Player("AAAA", new Bet(DEFAULT_BET_AMOUNT));
        cards.forEach(player::add);
        return player;
    }

    public static Player createPlayer(List<Card> cards, String playerName) {
        Player player = new Player(playerName, new Bet(DEFAULT_BET_AMOUNT));
        cards.forEach(player::add);
        return player;
    }

    public static Dealer createDealer(List<Card> cards) {
        Dealer dealer = new Dealer("AAAA");
        cards.forEach(dealer::add);
        return dealer;
    }
}
