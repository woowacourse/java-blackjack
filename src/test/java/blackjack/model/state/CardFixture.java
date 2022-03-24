package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSymbol;

public class CardFixture {
    public static final Card CLOVER_EIGHT = new Card(CardNumber.EIGHT, CardSymbol.CLOVER);
    public static final Card CLOVER_TWO = new Card(CardNumber.TWO, CardSymbol.CLOVER);
    public static final Card CLOVER_JACK = new Card(CardNumber.JACK, CardSymbol.CLOVER);
    public static final Card CLOVER_ACE = new Card(CardNumber.ACE, CardSymbol.CLOVER);
    public static final Card CLOVER_KING = new Card(CardNumber.KING, CardSymbol.CLOVER);
    public static final Card DIAMOND_JACK = new Card(CardNumber.JACK, CardSymbol.DIAMOND);
    public static final Card HEART_EIGHT = new Card(CardNumber.EIGHT, CardSymbol.HEART);
}
