package fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;

public class CardFixture {

    public static Card createAHeart() {
        return new Card(CardRank.ACE, CardSuit.HEART);
    }

    public static Card createADiamond() {
        return new Card(CardRank.ACE, CardSuit.DIAMOND);
    }

    public static Card create9Heart() {
        return new Card(CardRank.NINE, CardSuit.HEART);
    }

    public static Card create2Heart() {
        return new Card(CardRank.TWO, CardSuit.HEART);
    }
}
