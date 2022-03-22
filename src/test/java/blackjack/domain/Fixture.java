package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;

public class Fixture {

    public static final Card ACE_CLUBS = Card.valueOf(CardNumber.ACE, CardSymbol.CLUBS);
    public static final Card ACE_SPADES = Card.valueOf(CardNumber.ACE, CardSymbol.SPADES);
    public static final Card ACE_HEARTS = Card.valueOf(CardNumber.ACE, CardSymbol.HEARTS);
    public static final Card ACE_DIAMONDS = Card.valueOf(CardNumber.ACE, CardSymbol.DIAMONDS);

    public static final Card TEN_SPADES = Card.valueOf(CardNumber.TEN, CardSymbol.SPADES);

}
