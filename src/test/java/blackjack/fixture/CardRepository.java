package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSymbol;

public class CardRepository {

    public static final Card CLOVER_ACE = Card.of(CardRank.ACE, CardSymbol.CLOVER);
    public static final Card HEART_ACE = Card.of(CardRank.ACE, CardSymbol.HEART);
    public static final Card DIAMOND_ACE = Card.of(CardRank.ACE, CardSymbol.DIAMOND);

    public static final Card CLOVER2 = Card.of(CardRank.TWO, CardSymbol.CLOVER);
    public static final Card CLOVER3 = Card.of(CardRank.THREE, CardSymbol.CLOVER);
    public static final Card CLOVER4 = Card.of(CardRank.FOUR, CardSymbol.CLOVER);
    public static final Card CLOVER5 = Card.of(CardRank.FIVE, CardSymbol.CLOVER);
    public static final Card CLOVER6 = Card.of(CardRank.SIX, CardSymbol.CLOVER);
    public static final Card CLOVER7 = Card.of(CardRank.SEVEN, CardSymbol.CLOVER);
    public static final Card CLOVER8 = Card.of(CardRank.EIGHT, CardSymbol.CLOVER);
    public static final Card CLOVER10 = Card.of(CardRank.TEN, CardSymbol.CLOVER);
    public static final Card CLOVER_KING = Card.of(CardRank.KING, CardSymbol.CLOVER);
}
