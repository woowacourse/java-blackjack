package domain;

import static domain.card.Rank.ACE;
import static domain.card.Rank.EIGHT;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.FOUR;
import static domain.card.Rank.JACK;
import static domain.card.Rank.KING;
import static domain.card.Rank.NINE;
import static domain.card.Rank.QUEEN;
import static domain.card.Rank.SEVEN;
import static domain.card.Rank.SIX;
import static domain.card.Rank.TEN;
import static domain.card.Rank.THREE;
import static domain.card.Rank.TWO;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;

import domain.card.Card;
import domain.participant.Hands;
import java.util.List;

public class HandsTestFixture {

    public static final Hands sum10Size2 = new Hands(List.of(new Card(FIVE, SPADE), new Card(FIVE, HEART)));
    public static final Hands sum17Size3One = new Hands(
            List.of(new Card(SEVEN, SPADE), new Card(FOUR, SPADE), new Card(SIX, SPADE)));
    public static final Hands sum17Size3Two = new Hands(
            List.of(new Card(TEN, SPADE), new Card(THREE, SPADE), new Card(FOUR, SPADE)));
    public static final Hands sum18Size2 = new Hands(List.of(new Card(EIGHT, CLOVER), new Card(TEN, DIAMOND)));
    public static final Hands sum19Size4Ace11 = new Hands(
            List.of(new Card(ACE, DIAMOND), new Card(TWO, CLOVER), new Card(FOUR, CLOVER), new Card(TWO, CLOVER)));
    public static final Hands sum19Size3Ace1 = new Hands(
            List.of(new Card(ACE, HEART), new Card(NINE, SPADE), new Card(NINE, CLOVER)));
    public static final Hands sum20Size2 = new Hands(List.of(new Card(NINE, SPADE), new Card(ACE, SPADE)));
    public static final Hands sum20Size3 = new Hands(
            List.of(new Card(SEVEN, SPADE), new Card(TWO, SPADE), new Card(ACE, SPADE)));
    public static final Hands sum20Size3Ace1 = new Hands(
            List.of(new Card(ACE, DIAMOND), new Card(EIGHT, CLOVER), new Card(FIVE, CLOVER), new Card(SIX, CLOVER)));
    public static final Hands sum21Size3 = new Hands(
            List.of(new Card(QUEEN, HEART), new Card(SIX, SPADE), new Card(FIVE, CLOVER)));
    public static final Hands sum21Size3Ace11 = new Hands(
            List.of(new Card(ACE, HEART), new Card(EIGHT, SPADE), new Card(TWO, CLOVER)));
    public static final Hands bustHands = new Hands(
            List.of(new Card(EIGHT, DIAMOND), new Card(TWO, DIAMOND), new Card(TWO, DIAMOND), new Card(KING, CLOVER)));
    public static final Hands noBustHands = new Hands(List.of(new Card(JACK, HEART), new Card(TEN, SPADE)));
    public static final Hands blackJack = new Hands(List.of(new Card(JACK, HEART), new Card(ACE, SPADE)));
}
