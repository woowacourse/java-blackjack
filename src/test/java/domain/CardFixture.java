package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;

import java.util.List;

public class CardFixture {
    public static final Card HEART_ACE = new Card(Rank.ACE, Suit.HEART);
    public static final Card HEART_JACK = new Card(Rank.JACK, Suit.HEART);
    public static final Card HEART_TWO = new Card(Rank.TWO, Suit.HEART);
    public static final Card HEART_SEVEN = new Card(Rank.SEVEN, Suit.HEART);
    public static final Card HEART_EIGHT = new Card(Rank.EIGHT, Suit.HEART);
    public static final Card HEART_TEN = new Card(Rank.TEN, Suit.HEART);
    public static final Card HEART_KING = new Card(Rank.KING, Suit.HEART);
    public static final Card CLOVER_ACE = new Card(Rank.ACE, Suit.CLOVER);
    public static final Card CLOVER_SEVEN = new Card(Rank.SEVEN, Suit.CLOVER);
    public static final Card CLOVER_EIGHT = new Card(Rank.EIGHT, Suit.CLOVER);

    public static Cards blackjackCards() {
        return new Cards(List.of(CardFixture.HEART_ACE, CardFixture.HEART_JACK));
    }

    public static Cards fifteenCards() {
        return new Cards(List.of(CardFixture.CLOVER_SEVEN, CardFixture.CLOVER_EIGHT));
    }

    public static Cards twentyCards() {
        return new Cards(List.of(CardFixture.HEART_TEN, CardFixture.HEART_KING));
    }

    public static Cards twentyTwoCards() {
        return new Cards(List.of(CardFixture.HEART_TEN, CardFixture.HEART_KING, CardFixture.HEART_TWO));
    }

    public static Cards sixteenCardsWithAce() {
        return new Cards(List.of(CardFixture.HEART_SEVEN, CardFixture.HEART_EIGHT, CardFixture.HEART_ACE));
    }

    public static Cards seventeenCardsWithAces() {
        return new Cards(List.of(CardFixture.HEART_SEVEN, CardFixture.HEART_EIGHT, CardFixture.HEART_ACE, CardFixture.CLOVER_ACE));
    }

    public static Cards seventeenCards() {
        return new Cards(List.of(CardFixture.HEART_TEN, CardFixture.HEART_SEVEN));
    }
}
