package blackjack.domain.fixture;

import blackjack.domain.card.Card;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.EIGHT;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.SIX;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardShape.CLOVER;
import static blackjack.domain.card.CardShape.DIAMOND;
import static blackjack.domain.card.CardShape.HEART;
import static blackjack.domain.card.CardShape.SPADE;

public class FixtureCard {

    public static final Card 하트_10 = new Card(TEN, HEART);

    public static final Card 다이아몬드_6 = new Card(SIX, DIAMOND);

    public static final Card 스페이드_A = new Card(ACE, SPADE);
    public static final Card 스페이드_8 = new Card(EIGHT, SPADE);
    public static final Card 스페이드_9 = new Card(NINE, SPADE);
    public static final Card 스페이드_10 = new Card(TEN, SPADE);
    public static final Card 스페이드_Q = new Card(QUEEN, SPADE);

    public static final Card 클로버_A = new Card(ACE, CLOVER);
    public static final Card 클로버_2 = new Card(TWO, CLOVER);
    public static final Card 클로버_3 = new Card(THREE, CLOVER);
    public static final Card 클로버_9 = new Card(NINE, CLOVER);
    public static final Card 클로버_10 = new Card(TEN, CLOVER);
}
