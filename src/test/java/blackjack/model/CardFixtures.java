package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;

public class CardFixtures {


    public static final Card CLUB_ACE = Card.of(CardSuit.CLUB, CardNumber.ACE);
    public static final Card CLUB_THREE = Card.of(CardSuit.CLUB, CardNumber.THREE);
    public static final Card CLUB_FIVE = Card.of(CardSuit.CLUB, CardNumber.FIVE);
    public static final Card CLUB_EIGHT = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
    public static final Card CLUB_NINE = Card.of(CardSuit.CLUB, CardNumber.NINE);
    public static final Card CLUB_TEN = Card.of(CardSuit.CLUB, CardNumber.TEN);
    public static final Card CLUB_JACK = Card.of(CardSuit.CLUB, CardNumber.JACK);


    public static final Card HEART_ACE = Card.of(CardSuit.HEART, CardNumber.ACE);
    public static final Card HEART_FIVE = Card.of(CardSuit.HEART, CardNumber.FIVE);
    public static final Card HEART_SEVEN = Card.of(CardSuit.HEART, CardNumber.SEVEN);
    public static final Card HEART_EIGHT = Card.of(CardSuit.HEART, CardNumber.EIGHT);
    public static final Card HEART_NINE = Card.of(CardSuit.HEART, CardNumber.NINE);
    public static final Card HEART_TEN = Card.of(CardSuit.HEART, CardNumber.TEN);
    public static final Card HEART_JACK = Card.of(CardSuit.HEART, CardNumber.JACK);

}
