package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.Symbol;

public class CardFixture {
    public static final Card CLOVER_EIGHT = new Card(CardNumber.EIGHT, Symbol.CLOVER);
    public static final Card CLOVER_TWO = new Card(CardNumber.TWO, Symbol.CLOVER);
    public static final Card CLOVER_JACK = new Card(CardNumber.JACK, Symbol.CLOVER);
    public static final Card CLOVER_ACE = new Card(CardNumber.ACE, Symbol.CLOVER);
    public static final Card CLOVER_KING = new Card(CardNumber.KING, Symbol.CLOVER);
}
