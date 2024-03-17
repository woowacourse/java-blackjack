package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;

public enum CardFixture {

    ACE_SPADE_CARD(new Card(Rank.ACE, Suit.SPADE)),
    ACE_CLOVER_CARD(new Card(Rank.ACE, Suit.CLOVER)),
    ACE_DIAMOND_CARD(new Card(Rank.ACE, Suit.DIAMOND)),
    FOUR_SPADE_CARD(new Card(Rank.FOUR, Suit.SPADE)),
    FIVE_SPADE_CARD(new Card(Rank.FIVE, Suit.SPADE)),
    KING_SPADE_CARD(new Card(Rank.KING, Suit.SPADE)),
    THREE_SPADE_CARD(new Card(Rank.THREE, Suit.SPADE)),
    ;

    private final Card card;

    CardFixture(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}
