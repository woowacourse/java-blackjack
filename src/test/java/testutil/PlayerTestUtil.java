package testutil;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardShape;
import domain.participant.Bet;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.List;

public final class PlayerTestUtil {
    public static final long DEFAULT_BET_AMOUNT = 2_000_000;
    private static final List<Card> BLACKJACK_CARDS = List.of(
            new Card(CardShape.SPADE, CardRank.ACE),
            new Card(CardShape.HEART, CardRank.TEN)
    );

    public static Players createSinglePlayerSet(Player player) {
        return new Players(List.of(player));
    }

    public static Player createBlackjackPlayer() {
        return createPlayer(BLACKJACK_CARDS);
    }

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

    public static Dealer createBlackjackDealer() {
        return createDealer(BLACKJACK_CARDS);
    }

    public static Dealer createDealer(List<Card> cards) {
        Dealer dealer = new Dealer("AAAA");
        cards.forEach(dealer::add);
        return dealer;
    }
}
