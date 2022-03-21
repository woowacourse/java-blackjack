package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Player;

public class Fixture {

    public static final Card ACE_CLOVER = new Card(Denomination.ACE, Suit.CLOVER);
    public static final Card KING_CLOVER = new Card(Denomination.KING, Suit.CLOVER);
    public static final Card TEN_CLOVER = new Card(Denomination.TEN, Suit.CLOVER);
    public static final Card NINE_CLOVER = new Card(Denomination.NINE, Suit.CLOVER);
    public static final Card FIVE_CLOVER = new Card(Denomination.FIVE, Suit.CLOVER);

    public static final Card ACE_HEART = new Card(Denomination.ACE, Suit.HEART);
    public static final Card KING_HEART = new Card(Denomination.KING, Suit.HEART);
    public static final Card TEN_HEART = new Card(Denomination.TEN, Suit.HEART);
    public static final Card NINE_HEART = new Card(Denomination.NINE, Suit.HEART);
    public static final Card FIVE_HEART = new Card(Denomination.FIVE, Suit.HEART);

    public static void takeTwoCards(Player player, Card card1, Card card2) {
        player.takeCard(card1);
        player.takeCard(card2);
    }

    public static void takeThreeCards(Player player, Card card1, Card card2, Card card3) {
        player.takeCard(card1);
        player.takeCard(card2);
        player.takeCard(card3);
    }
}
