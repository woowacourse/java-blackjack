package blackjack.model.card;

public class CardFixtures {

    public static Card SPADE_ACE_CARD = new Card(Suit.SPADES, CardValue.ACE);
    public static Card SPADE_TWO_CARD = new Card(Suit.SPADES, CardValue.TWO);
    public static Card SPADE_FIVE_CARD = new Card(Suit.SPADES, CardValue.FIVE);
    public static Card SPADE_SIX_CARD = new Card(Suit.SPADES, CardValue.SIX);
    public static Card SPADE_TEN_CARD = new Card(Suit.SPADES, CardValue.TEN);

    public static final CardShuffler NO_SHUFFLER = cards -> {};

    private CardFixtures() {
    }

    public static Card createCard(Suit suit, CardValue cardValue) {
        return new Card(suit, cardValue);
    }
}
