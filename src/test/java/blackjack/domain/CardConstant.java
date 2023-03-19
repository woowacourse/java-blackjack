package blackjack.domain;

import blackjack.domain.card.Card;

import static blackjack.domain.card.Letter.*;
import static blackjack.domain.card.Shape.*;

public final class CardConstant {

    public static final Card DIAMOND_ACE = Card.of(DIAMOND, ACE);
    public static final Card SPACE_ACE = Card.of(SPADE, ACE);
    public static final Card CLOVER_ACE = Card.of(CLOVER, ACE);

    public static final Card DIAMOND_TWO = Card.of(DIAMOND, TWO);
    public static final Card HEART_TWO = Card.of(HEART, TWO);
    public static final Card SPACE_TWO = Card.of(SPADE, TWO);

    public static final Card DIAMOND_THREE = Card.of(DIAMOND, THREE);
    public static final Card HEART_THREE = Card.of(HEART, THREE);

    public static final Card CLOVER_SIX = Card.of(CLOVER, SIX);

    public static final Card DIAMOND_SEVEN = Card.of(DIAMOND, SEVEN);
    public static final Card HEART_SEVEN = Card.of(HEART, SEVEN);

    public static final Card DIAMOND_EIGHT = Card.of(DIAMOND, EIGHT);
    public static final Card HEART_EIGHT = Card.of(HEART, EIGHT);

    public static final Card DIAMOND_NINE = Card.of(DIAMOND, NINE);

    public static final Card DIAMOND_TEN = Card.of(DIAMOND, TEN);
    public static final Card HEART_TEN = Card.of(HEART, TEN);

    public static final Card DIAMOND_JACK = Card.of(DIAMOND, JACK);
}
