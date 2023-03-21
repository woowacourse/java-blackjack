package domain;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Hand;
import domain.card.Suit;
import domain.player.Bet;
import domain.player.Name;
import domain.player.Player;

import java.util.List;

public class Textures {
    private Textures() {
    }

    public static final Card DIAMOND_ACE = new Card(Suit.DIAMOND, Denomination.ACE);
    public static final Card DIAMOND_FIVE = new Card(Suit.DIAMOND, Denomination.FIVE);
    public static final Card DIAMOND_SEVEN = new Card(Suit.DIAMOND, Denomination.SEVEN);
    public static final Card DIAMOND_NINE = new Card(Suit.DIAMOND, Denomination.NINE);
    public static final Card DIAMOND_KING = new Card(Suit.DIAMOND, Denomination.KING);

    public static final Card HEART_ACE = new Card(Suit.HEART, Denomination.ACE);
    public static final Card HEART_SIX = new Card(Suit.HEART, Denomination.SIX);
    public static final Card HEART_TEN = new Card(Suit.HEART, Denomination.TEN);
    public static final Card HEART_SEVEN = new Card(Suit.HEART, Denomination.SEVEN);
    public static final Card HEART_EIGHT = new Card(Suit.HEART, Denomination.EIGHT);
    public static final Card HEART_NINE = new Card(Suit.HEART, Denomination.NINE);

    public static final Card SPADE_ACE = new Card(Suit.SPADE, Denomination.ACE);
    public static final Card SPADE_TWO = new Card(Suit.SPADE, Denomination.TWO);
    public static final Card SPADE_THREE = new Card(Suit.SPADE, Denomination.THREE);
    public static final Card SPADE_FOUR = new Card(Suit.SPADE, Denomination.FOUR);
    public static final Card SPADE_EIGHT = new Card(Suit.SPADE, Denomination.EIGHT);
    public static final Card SPADE_KING = new Card(Suit.SPADE, Denomination.KING);
    public static final Card SPADE_QUEEN = new Card(Suit.SPADE, Denomination.QUEEN);

    public static final Card CLOVER_ACE = new Card(Suit.CLOVER, Denomination.ACE);
    public static final Card CLOVER_THREE = new Card(Suit.CLOVER, Denomination.THREE);

    public static Player makeDealerWithBet(Bet bet) {
        return new Player(Hand.withEmptyHolder(), Name.dealerName(), bet);
    }

    public static Player makeDealerWithCards(List<Card> cards) {
        return new Player(new Hand(cards), Name.dealerName(), Bet.from(3000));
    }

    public static Player makeDealer() {
        return makeDealerWithBet(Bet.from(3000));
    }

    public static Player makePlayerWithCards(List<Card> cards) {
        return new Player(
                new Hand(cards),
                Name.of("참가자"),
                Bet.from(3000)
        );
    }
}
